package com.example.functionalDemo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import java.util.function.Function;

@Slf4j
@SpringBootApplication
@Controller
public class FunctionalDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunctionalDemoApplication.class, args);


    }







}


