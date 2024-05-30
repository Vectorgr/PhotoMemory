package net.flashbacks.api.services;

import net.flashbacks.api.models.LocationModel;
import net.flashbacks.api.repositories.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {
    @Autowired
    ILocationRepository locationRepository;

    public ArrayList<LocationModel> getAll(){
        return (ArrayList<LocationModel>) locationRepository.findAll();
    }

    @Transactional
    public LocationModel saveLocation(LocationModel location){
        return locationRepository.save(location);
    }

    public Optional<LocationModel> getLocation(UUID id){
        return locationRepository.findById(id);
    }

    @Transactional
    public LocationModel updateLocation(LocationModel request, UUID id){
        LocationModel location = locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Location not found"));

        location.setName(request.getName());
        location.setDescription(request.getDescription());
        location.setLatitude(request.getLatitude());
        location.setLongitude(request.getLongitude());
        try {
            location.setImage(request.getImage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return locationRepository.save(location);
    }

    @Transactional
    public Boolean deleteLocation(UUID id){
        try {
            locationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
