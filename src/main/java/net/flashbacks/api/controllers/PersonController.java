package net.flashbacks.api.controllers;

import net.flashbacks.api.models.ImageModel;
import net.flashbacks.api.models.PersonModel;
import net.flashbacks.api.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/people")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public ArrayList<PersonModel> getAll(){
        return this.personService.getAll();
    }

    //Request body lee los datos que le pasamos
    @PostMapping
    public PersonModel savePerson(@RequestBody PersonModel person){
        return this.personService.savePerson(person);
    }

    @GetMapping(path = "/{id}")
    public Optional<PersonModel> getPerson(@PathVariable("id") UUID id){
        return this.personService.getPerson(id);
    }

    @PutMapping(path = "/{id}")
    public PersonModel updatePerson(@RequestBody PersonModel request, @PathVariable("id") UUID id){
        return this.personService.updatePerson(request,id);
    }
    @DeleteMapping(path = "/{id}")
    public String deletePerson(@RequestBody PersonModel request, @PathVariable("id") UUID id){
        boolean ok = this.personService.deletePerson(id);

        if(ok){
            return "User deleted ("+id+")";
        }else{
            return "Error in delete ("+id+")";
        }
    }
    //Profile picture
    //TODO return response
    @PutMapping(path = "/{id}/image")
    public PersonModel  saveProfilePic(@RequestParam("image") MultipartFile image, @PathVariable("id") UUID id) throws IOException, SQLException {
        if(this.getPerson(id).isPresent()){
            PersonModel person = getPerson(id).get();
            person.setProfilePic(image.getBytes());
            return this.updatePerson(person, person.getId());
        }
        return null;
    }

    @GetMapping(path = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getProfilePic(@PathVariable("id") UUID id) throws SQLException {

        if(this.getPerson(id).isPresent()){
            return this.getPerson(id).get().getProfilePic();

        }else{
            System.out.println("NO EXISTE:(");
        }
        return null;

    }



}
