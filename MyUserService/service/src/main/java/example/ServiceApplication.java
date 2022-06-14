package example;

import example.mapper.MyUserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceApplication {


    @Bean
    public MyUserMapper myUserMapper(){
        return new MyUserMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

}
