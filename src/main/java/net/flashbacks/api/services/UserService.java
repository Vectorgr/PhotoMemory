package net.flashbacks.api.services;

import net.flashbacks.api.models.PersonModel;
import net.flashbacks.api.models.UserModel;
import net.flashbacks.api.repositories.IPersonRepository;
import net.flashbacks.api.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;

    // In your service:
    @Transactional
    public UserModel saveUser(UserModel user) {
        System.out.println("Guardando: " + user.getName());
        UserModel savedUser = userRepository.save(user);
        System.out.println("User has been saved: " + savedUser.getName());
        return savedUser;
    }

    //Puede devolver PeopleModel o no
    public Optional<UserModel> getUser(UUID id){
        return userRepository.findById(id);
    }
    public UserModel getUserByName(String name){
        return userRepository.findByName(name);


    }
    @Transactional
    public UserModel updateUser(UserModel request, UUID id){
        UserModel user = userRepository.findById(id).get();

        user.setName(request.getName());
        user.setAdmin(request.isAdmin());

        return user;
    }
    @Transactional
    public Boolean deleteUser(UUID id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }


    public ArrayList<UserModel> getAll() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }
}
