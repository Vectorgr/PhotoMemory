package net.flashbacks.api.controllers;

import net.flashbacks.api.models.MemoryModel;
import net.flashbacks.api.models.PersonModel;
import net.flashbacks.api.models.UserModel;
import net.flashbacks.api.services.MemoryService;
import net.flashbacks.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ArrayList<UserModel> getAll(){
        return this.userService.getAll();
    }

    //Request body lee los datos que le pasamos

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user) throws IOException {

        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUser(@PathVariable("id") UUID id){
        return this.userService.getUser(id);
    }

    @PutMapping(path = "/{id}")
    public UserModel updateUser(@RequestBody UserModel request,@PathVariable("id") UUID id){
        return this.userService.updateUser(request,id);
    }
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable("id") UUID id){
        boolean ok = this.userService.deleteUser(id);

        if(ok){
            return "User deleted ("+id+")";
        }else{
            return "Error in delete ("+id+")";
        }
    }




}
