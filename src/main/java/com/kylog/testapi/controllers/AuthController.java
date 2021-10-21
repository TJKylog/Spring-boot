package com.kylog.testapi.controllers;

import com.kylog.testapi.dao.UserDao;
import com.kylog.testapi.models.User;
import com.kylog.testapi.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        User user_logged = userDao.login(user);
        if (user_logged != null) {
            String token = jwtUtil.create(user_logged.getId().toString(),user_logged.getEmail());
            return token;
        }
        else {
            return "Fail";
        }
    }
}
