package net.flashbacks.api.controllers;

import net.flashbacks.api.models.LocationModel;
import net.flashbacks.api.models.LocationModel;
import net.flashbacks.api.services.LocationService;
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
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ArrayList<LocationModel> getAll(){
        return this.locationService.getAll();
    }

    //Request body lee los datos que le pasamos
    @PostMapping
    public LocationModel saveLocation(@RequestBody LocationModel location){
        return this.locationService.saveLocation(location);
    }

    @GetMapping(path = "/{id}")
    public Optional<LocationModel> getLocation(@PathVariable("id") UUID id){
        return this.locationService.getLocation(id);
    }

    @PutMapping(path = "/{id}")
    public LocationModel updateLocation(@RequestBody LocationModel request, @PathVariable("id") UUID id){
        return this.locationService.updateLocation(request,id);
    }
    @DeleteMapping(path = "/{id}")
    public String deleteLocation(@RequestBody LocationModel request, @PathVariable("id") UUID id){
        boolean ok = this.locationService.deleteLocation(id);

        if(ok){
            return "User deleted ("+id+")";
        }else{
            return "Error in delete ("+id+")";
        }
    }

    //Profile picture
    @PostMapping(path = "/{id}/image")
    public LocationModel  saveImage(@RequestParam("image") MultipartFile image, @PathVariable("id") UUID id) throws IOException, SQLException {
        if(this.getLocation(id).isPresent()){
            LocationModel location = getLocation(id).get();
            location.setImage(image.getBytes());
            return this.updateLocation(location, id);
        }
        return null;
    }

    @GetMapping(path = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable("id") UUID id) throws SQLException {

        if(this.getLocation(id).isPresent()){
            return this.getLocation(id).get().getImage();

        }else{
            System.out.println("NO EXISTE:(");
        }
        return null;

    }



}
