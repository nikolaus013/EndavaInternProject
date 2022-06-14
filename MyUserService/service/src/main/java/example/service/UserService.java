package example.service;

import dto.MyUserDto;
import example.mapper.MyUserMapper;
import example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MyUserMapper myUserMapper;


    public Mono<Void> saveUser(MyUserDto userDto) {


        return userRepository.save(myUserMapper.dtoToEntity(userDto)).then();
    }

    public Flux<MyUserDto> getAllUsers() {
        return userRepository.findAll().map(myUserMapper::entityToDTO);
    }

    public Flux<MyUserDto> findByUsername(String username) {
        return userRepository.findByUsername(username).map(myUserMapper::entityToDTO);
    }


}
