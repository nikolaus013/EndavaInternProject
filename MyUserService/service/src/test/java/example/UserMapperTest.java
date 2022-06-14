package example;

import dto.MyUserDto;
import example.mapper.MyUserMapper;
import example.models.MyUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@WebMvcTest(MyUserMapper.class)
@ExtendWith(SpringExtension.class)
public class UserMapperTest {

    @Autowired
    MyUserMapper myUserMapper;


    @Test
    void entityToDtoTest() {

        MyUserDto userDto = new MyUserDto();
        userDto.setUsername("test");
        userDto.setName("Djura");
        userDto.setSurname("BlaBla");

        MyUser user = new MyUser();
        user.setUsername("test");
        user.setName("Djura");
        user.setSurname("BlaBla");
        var user1Dto = myUserMapper.entityToDTO(user);

        Assertions.assertEquals(userDto, user1Dto);

    }


    @Test
    void dtoToEntityTest() {

        MyUserDto userDto = new MyUserDto();
        userDto.setUsername("test");
        userDto.setName("Djura");
        userDto.setSurname("BlaBla");

        MyUser user = new MyUser();
        user.setUsername("test");
        user.setName("Djura");
        user.setSurname("BlaBla");
        var user1Dto = myUserMapper.dtoToEntity(userDto);

        Assertions.assertEquals(userDto.getUsername(), user1Dto.getUsername());

    }
}
