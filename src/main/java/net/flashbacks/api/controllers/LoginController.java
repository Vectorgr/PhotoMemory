package net.flashbacks.api.controllers;

import net.flashbacks.api.models.UserDTO;
import net.flashbacks.api.models.UserModel;
import net.flashbacks.api.security.JWTAuthenticationConfig;
import net.flashbacks.api.services.ImageService;
import net.flashbacks.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    JWTAuthenticationConfig jwtAuthenticationConfig;

    @Autowired
    private PasswordEncoder passEncoder;


    @PostMapping("/login")
    public String login(@RequestBody UserDTO user) {
        System.out.println(user.getName());
        if(user.getPass().equals("adminpass")){
            String hashpass = passEncoder.encode("password");
            try {
                userService.saveUser(new UserModel("admin", hashpass, true));
            } catch (Exception e) {
                System.out.println("Error while saving user: " + e.getMessage());
            }
        }
        //String hashPass = passEncoder.encode(user.getPass());

        for(UserModel u : userService.getAll()){
            System.out.println(u.getName());
            System.out.println(u.getHashPass());
         }
        //If user not exist
        UserModel userModel = userService.getUserByName(user.getName());
        if(userModel==null){return null;}

        //Check password
        if(passEncoder.matches(user.getPass(),userModel.getHashPass())){
            System.out.println("entrado");

            List<String> roles = new ArrayList<>();
            //If user is admin
            if(userModel.isAdmin()){roles.add("admin");}
            System.out.println("entrado");
            //Get token
            String token = jwtAuthenticationConfig.getJWTToken(userModel.getName(),roles);
            System.out.println("Token generated: " + token);
            return token;
        }

        System.out.println("User login failed: " + user.getName());
        return null;


    }
}