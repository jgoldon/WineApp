package com.wine.controller;


import com.wine.model.*;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Judith on 21.05.2017.
 */
@RestController
@RequestMapping("api/v1/")
public class UserController {
    public UserController(UserDao userDao, TokenDao tokenDao){
        this.userDao = userDao;
        this.tokenDao = tokenDao;
    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @RequestMapping(value  = "users", method = RequestMethod.POST)
    public void create(@RequestBody RegisterUserRequest request){
        User user = new User(request.username, request.password);
        userDao.save(user);
    }

    @RequestMapping(value  = "users/auth", method = RequestMethod.POST)
    public LoginUserResponse create(@RequestBody LoginUserRequest request) throws NotFoundException {
        User user = userDao.getUserByUsername(request.username);
        boolean match = user.isMatch(request.password);

        if(match == false){
            throw new NotFoundException("Login not found");
        }

        Token token = new Token(request.username);
        tokenDao.save(token);

        return new LoginUserResponse(token.toString());
    }
}
