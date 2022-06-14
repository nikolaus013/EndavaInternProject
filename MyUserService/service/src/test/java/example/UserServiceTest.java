package example;

import dto.MyUserDto;
import example.mapper.MyUserMapper;
import example.models.MyUser;
import example.repositories.UserRepository;
import example.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@WebFluxTest(UserService.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @MockBean
    UserService userService;
    @Mock
    MyUserMapper myUserMapper;

    @Test
    void getAllUsersTest() {
//        MyUserDto userDto = new MyUserDto();
//        userDto.setUsername("test");
//        userDto.setName("Djura");
//        userDto.setSurname("BlaBla");

        MyUser user = new MyUser();
        user.setUsername("test");
        user.setName("Djura");
        user.setSurname("BlaBla");

        Flux<MyUser> userFlux = Flux.just(user);
        userRepository.findAll().subscribe();
        Mockito.when(userRepository.findAll()).thenReturn(userFlux);

     //   Assertions.assertNotNull(userFlux);
      //  Assertions.assertEquals(userRepository.findAll(), userFlux);
      //  Mockito.verify(userRepository,Mockito.times(1)).findAll();
        Mockito.verify(userRepository,Mockito.notNull()).findAll();

    }
}
