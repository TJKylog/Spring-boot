package com.kylog.testapi.controllers;

import com.kylog.testapi.dao.UserDao;
import com.kylog.testapi.models.User;
import com.kylog.testapi.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "user/{id}")
    public User getUser(@PathVariable Long id){
        User user = new User();
        user.setId(id);
        user.setName("Pedro");
        user.setEmail("correo@correo.com");
        user.setPhone("21212");
        user.setPassword("pass");
        return user;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public void register(@RequestBody User user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);
        userDao.register(user);
        return;
    }

    @RequestMapping(value = "users")
    public List<User> getUsers(@RequestHeader(value = "Authorization")  String token){
        if(this.validate_token(token))
        {
            return userDao.getUsers();
        }
        else {
            return  new ArrayList<>();
        }
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id){
        userDao.delete(id);
    }

    private boolean validate_token(String token){
        String user_id = jwtUtil.getKey(token);
        if(user_id != null)
        {
            return true;
        }
        return false;
    }
}
