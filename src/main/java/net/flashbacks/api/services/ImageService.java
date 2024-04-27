package net.flashbacks.api.services;

import net.flashbacks.api.models.ImageModel;
import net.flashbacks.api.models.MemoryModel;
import net.flashbacks.api.repositories.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    IImageRepository imageRepository;

    public ArrayList<ImageModel> getImages(){
        return (ArrayList<ImageModel>) imageRepository.findAll();
    }
    //Puede devolver MemoryModel o no
    public Optional<ImageModel> getImage(UUID id){return imageRepository.findById(id);}

    @Transactional
    public ImageModel saveImage(ImageModel image){return imageRepository.save(image);}
    @Transactional
    public ImageModel updateImage(ImageModel request, UUID id) throws SQLException {
        ImageModel image = imageRepository.findById(id).get();

        image.setId(request.getId());
        image.setImage_data(request.getImage_data());
        image.setImage_name(request.getImage_name());
        image.setImage_path(request.getImage_path());

        return image;
    }
    @Transactional
    public Boolean deleteImage(UUID id){
        try{
            imageRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
