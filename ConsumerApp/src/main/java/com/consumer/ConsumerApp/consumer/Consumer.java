package com.consumer.ConsumerApp.consumer;

import com.consumer.ConsumerApp.models.MyUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
@Configuration
public class    Consumer {

    WebClient webClient = WebClient.create("http://localhost:8082");


    public void msgAck(Message<MyUserDto> message) {
        Optional.ofNullable(message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class)).ifPresent(Acknowledgment::acknowledge);
    }


    @Bean
    public Function<Flux<Message<MyUserDto>>, Mono<Void>> consume() {

        return p -> p
                .flatMap(this::userMessage)
                .then();
    }


    private Flux<MyUserDto> getByUsername(String username) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("user/findUsername/{username}")
                        .build(username))
                .retrieve()
                .bodyToFlux(MyUserDto.class);
    }

    private Flux<MyUserDto> saveUser(MyUserDto myUserDto) {
        return webClient.post().uri("/user/post").body(Mono.just(myUserDto), MyUserDto.class)
                .retrieve()
                .bodyToFlux(MyUserDto.class);
    }


    private Flux<MyUserDto> userMessage(Message<MyUserDto> message) {
        return getByUsername(message.getPayload().getUsername())
                .doOnNext(response -> System.out.println("User with username " + response.getUsername() + " Already exists"))
                .switchIfEmpty(saveUser(message.getPayload())
                        .onErrorResume(e -> {
                            System.out.println("Name too short, Renaming...");
                            var newUserDto = new MyUserDto();
                            newUserDto = message.getPayload();
                            newUserDto.setName(newUserDto.getName() + "??");
                            System.out.println("User" + newUserDto + " is Created");
                            return saveUser(newUserDto);

                        })
                )
                .onErrorResume(response -> System.err::println)
                .doOnComplete(() -> {
                    System.out.println("Acking message");
                    msgAck(message);
                });


    }


}