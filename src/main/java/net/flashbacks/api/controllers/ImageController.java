package net.flashbacks.api.controllers;

import net.flashbacks.api.models.ImageModel;
import net.flashbacks.api.models.MemoryModel;
import net.flashbacks.api.services.ImageService;
import net.flashbacks.api.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.List;


@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping
    public ArrayList<ImageModel> getImages(){
        return this.imageService.getImages();
    }

    //Request body lee los datos que le pasamos
    @PostMapping
    public ImageModel saveImage(@RequestParam("parent_memory") UUID parent_memory, @RequestParam("image") MultipartFile image) throws IOException, SQLException {

        ImageModel imageMdl = new ImageModel(image.getName(),image.getBytes(), parent_memory);

        return this.imageService.saveImage(imageMdl);
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable("id") UUID id) throws SQLException {
        Optional<ImageModel> oImageModel = this.imageService.getImage(id);
        if(oImageModel.isPresent()){
            return oImageModel.get().getImage_data();
        }
        return null;
    }
    @PutMapping(path = "/{id}")
    public ImageModel updateImage(@RequestParam("parent_memory") UUID parent_memory,@RequestParam("image") MultipartFile image,@PathVariable("id") UUID id) throws SQLException, IOException {
        ImageModel imageMdl = new ImageModel(image.getName(),image.getBytes(),parent_memory);

        return this.imageService.updateImage(imageMdl, id);
    }
    @DeleteMapping(path = "/{id}")
    public String deleteImage(@RequestBody ImageModel request,@PathVariable("id") UUID id){
        boolean ok = this.imageService.deleteImage(id);

        if(ok){
            return "User deleted ("+id+")";
        }else{
            return "Error in delete ("+id+")";
        }
    }

    //Get images by memoryid
    @GetMapping("/")
    @ResponseBody
    public List<ImageModel> getImagesByMemory(@RequestParam UUID memoryid) {
        return this.imageService.getImagesByMemory(memoryid);
    }






}
