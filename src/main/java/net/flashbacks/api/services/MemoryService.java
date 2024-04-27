package net.flashbacks.api.services;

import net.flashbacks.api.models.MemoryModel;
import net.flashbacks.api.repositories.IMemoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemoryService {
    @Autowired
    IMemoryRespository memoryRespository;

    public ArrayList<MemoryModel> getMemories(){
        return (ArrayList<MemoryModel>) memoryRespository.findAll();
    }

    @Transactional
    public MemoryModel saveMemory(MemoryModel memory){

        return memoryRespository.save(memory);
    }

    //Puede devolver MemoryModel o no
    public Optional<MemoryModel> getMemory(UUID id){
        return memoryRespository.findById(id);
    }

    @Transactional

    public MemoryModel updateMemory(MemoryModel request, UUID id){
        MemoryModel memory = memoryRespository.findById(id).get();

        memory.setTitle(request.getTitle());
        memory.setDescription(request.getDescription());
        memory.setDate(request.getDate());
        memory.setImage(request.getImage());

        return memory;
    }
    @Transactional
    public Boolean deleteMemory(UUID id){
        try{
            memoryRespository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
