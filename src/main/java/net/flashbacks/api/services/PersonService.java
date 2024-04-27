package net.flashbacks.api.services;

import net.flashbacks.api.models.PersonModel;
import net.flashbacks.api.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    @Autowired
    IPersonRepository peopleRepository;

    public ArrayList<PersonModel> getAll(){
        return (ArrayList<PersonModel>) peopleRepository.findAll();
    }
    @Transactional

    public PersonModel savePerson(PersonModel person){

        return peopleRepository.save(person);
    }

    //Puede devolver PeopleModel o no
    public Optional<PersonModel> getPerson(UUID id){
        return peopleRepository.findById(id);
    }
    @Transactional
    public PersonModel updatePerson(PersonModel request, UUID id){
        PersonModel person = peopleRepository.findById(id).get();

        person.setName(request.getName());
        person.setBiography(request.getBiography());
        person.setBirthDate(request.getBirthDate());

        return person;
    }
    @Transactional
    public Boolean deletePerson(UUID id){
        try{
            peopleRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }




}
