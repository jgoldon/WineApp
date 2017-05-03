package com.wine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Judith on 26.03.2017.
 */
@RestController
public class HomeController {

        @RequestMapping("/")
        public String home(){
            return "It works";
        }
}
