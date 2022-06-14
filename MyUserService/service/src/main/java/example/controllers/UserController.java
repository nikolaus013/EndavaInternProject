package example.controllers;


import dto.MyUserDto;
import example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @PostMapping(value = "/post")
    public Mono<Void> createUser(@Valid @RequestBody MyUserDto myUserDto) {

        return userService.saveUser(myUserDto);
    }


    @GetMapping(value = "getAll")
    public Flux<MyUserDto> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping(value = "/findUsername/{username}")
    public Flux<MyUserDto> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }


}
