package example.mapper;


import dto.MyUserDto;
import example.models.MyUser;



public class MyUserMapper {


    public MyUserDto entityToDTO(MyUser myUser) {

        var myUserDto = new MyUserDto();

        myUserDto.setUsername(myUser.getUsername());
        myUserDto.setName(myUser.getName());
        myUserDto.setSurname(myUser.getSurname());
        return myUserDto;
    }

    public MyUser dtoToEntity(MyUserDto userDto) {
        var myUser = new MyUser();
        myUser.setUsername(userDto.getUsername());
        myUser.setName(userDto.getName());
        myUser.setSurname(userDto.getSurname());
        return myUser;
    }
}
