package net.flashbacks.api.controllers;

import net.flashbacks.api.models.MemoryModel;
import net.flashbacks.api.services.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

/*
La peticion será post
 http://.../{uuid}/
 {
  "title": "Feliz dia probando esto",
  "description": "Esto es la descripcion del pmermeireoroeoreireioroienurenjkn okrmer oi rejiorej  y asi finalizaria.",
  "date": "10/05/2024"
}
 */
@RestController
@RequestMapping("/")
public class MemoryController {
    @Autowired
    private MemoryService memoryService;

    @GetMapping
    public ArrayList<MemoryModel> getMemories(){
        return this.memoryService.getMemories();
    }

    //Request body lee los datos que le pasamos

    @PostMapping
    public MemoryModel saveMemory(@RequestBody MemoryModel memory) throws IOException {

        return this.memoryService.saveMemory(memory);
    }

    @GetMapping(path = "/{id}")
    public Optional<MemoryModel> getMemory(@PathVariable("id") UUID id){
        return this.memoryService.getMemory(id);
    }

    @PutMapping(path = "/{id}")
    public MemoryModel updateMemory(@RequestBody MemoryModel request,@PathVariable("id") UUID id){
        return this.memoryService.updateMemory(request,id);
    }
    @DeleteMapping(path = "/{id}")
    public String deleteMemory(@RequestBody MemoryModel request,@PathVariable("id") UUID id){
        boolean ok = this.memoryService.deleteMemory(id);

        if(ok){
            return "User deleted ("+id+")";
        }else{
            return "Error in delete ("+id+")";
        }
    }





}
