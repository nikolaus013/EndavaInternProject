package example;


import dto.MyUserDto;
import example.controllers.UserController;
import example.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@WebFluxTest(UserController.class)
//@SpringBootTest(classes = {UserService.class, UserRepository.class})
public class UserControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    private UserService userService;


    @Test
    public void getAllUsersTest() {

        MyUserDto user = new MyUserDto();
        user.setUsername("test");
        user.setName("Djura");
        user.setSurname("BlaBla");


        MyUserDto user1 = new MyUserDto();
        user.setUsername("test1");
        user.setName("Djura");
        user.setSurname("BlaBla");

        Flux<MyUserDto> userFlux = Flux.just(user, user1);


        Mockito
                .when(userService.getAllUsers())
                .thenReturn(userFlux);
        webTestClient.get().uri("/user/getAll").exchange().expectStatus().isOk().expectBodyList(MyUserDto.class);
        Assertions.assertNotNull(userFlux);
        //  Mockito.verify(userService.getAllUsers());


    }


    @Test
    public void testGetByUsername() {
        MyUserDto user = new MyUserDto();
        user.setUsername("test");
        user.setName("Djura");
        user.setSurname("BlaBla");

        List<MyUserDto> users = new ArrayList<>();
        users.add(user);


        Flux<MyUserDto> userFlux = Flux.fromIterable(users);

        Mockito
                .when(userService.findByUsername("test"))
                .thenReturn(userFlux);


        webTestClient.get().uri("/user/findUsername/{username}", "test")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(MyUserDto.class).consumeWith(listEntityExchangeResult -> {
                    var userDto = listEntityExchangeResult.getResponseBody();//Lista myUserDto
                    Assertions.assertEquals(userDto.get(0).getName(), "Djura");
                });


        Mockito.verify(userService).findByUsername("test");
    }

    @Test
    public void createUserTest() {
        MyUserDto user = new MyUserDto();
        user.setUsername("test");
        user.setName("Djura");
        user.setSurname("BlaBla");


        Mockito.when(userService.saveUser(user)).thenReturn(null);
        webTestClient.post().uri("/user/post")
                .body(BodyInserters.fromValue(user))
                .exchange()
                .expectStatus()
                .isOk();

        Mockito.verify(userService, Mockito.times(1)).saveUser(user);

    }


}
