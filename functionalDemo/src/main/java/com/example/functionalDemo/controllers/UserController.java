package com.example.functionalDemo.controllers;

import com.example.functionalDemo.models.MyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/produce")
public class UserController {

    private final StreamBridge streamBridge;



    @PostMapping("/user")
    public ResponseEntity<String> publish(@RequestBody MyUser myUser) {
        streamBridge.send("publish-out-0",myUser);
        return  new ResponseEntity<>("Success" ,HttpStatus.OK);

    }



}

