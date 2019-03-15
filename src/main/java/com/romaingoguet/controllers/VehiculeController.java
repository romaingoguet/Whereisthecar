package com.romaingoguet.controllers;

import com.romaingoguet.exceptions.ResourceNotFoundException;
import com.romaingoguet.models.User;
import com.romaingoguet.models.UserVehicule;
import com.romaingoguet.models.Vehicule;
import com.romaingoguet.repositories.UserRepository;
import com.romaingoguet.repositories.UserVehiculeRepository;
import com.romaingoguet.repositories.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class VehiculeController {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserVehiculeRepository userVehiculeRepository;

    /**
     * GET on all vehicules
     * @return Vehicule List
     */
    @GetMapping("/vehicules")
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    /**
     * Get a vehicule
     * @param vehiculeId
     * @return Vehicule
     * @throws ResourceNotFoundException
     */
    @GetMapping("/vehicules/{id}")
    public ResponseEntity<Vehicule> getVehiculesById(@PathVariable(value = "id") Long vehiculeId)
            throws ResourceNotFoundException {
        Vehicule vehicule =
                vehiculeRepository
                        .findById(vehiculeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Vehicule not found on : " + vehiculeId));
        return ResponseEntity.ok().body(vehicule);
    }

    /**
     * Get all the vehicules associted with a user
     * @param id
     * @return Vehicule List
     */
    @GetMapping("/users/{id}/vehicules")
    public List<Vehicule> getVehiculesByUserId(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User : " + id + " not found"));
        return vehiculeRepository.findByUserId(id);
    }

    // Remplacer par POST /users/{id}/vehicules/
    /*
    @PostMapping("/vehicules")
    public Vehicule createVehicule(@Valid @RequestBody Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }*/

    /**
     * add a vehicule and associate with the user
     * @param userId
     * @param vehicule
     * @return Vehicule
     * @throws ResourceNotFoundException
     */
    @PostMapping("/users/{id}/vehicules")
    public Vehicule createVehicule(@PathVariable(value = "id") Long userId, @Valid @RequestBody Vehicule vehicule) throws ResourceNotFoundException {
        Vehicule newVehicule = vehiculeRepository.save(vehicule);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User : " + userId + " not found"));
        UserVehicule userVehicule = new UserVehicule(user, newVehicule, true);
        userVehiculeRepository.save(userVehicule);
        return newVehicule;
    }

    /**
     * update a vehicule
     * @param vehiculeId
     * @param vehiculeDetails
     * @return Vehicule
     * @throws ResourceNotFoundException
     */
    @PutMapping("/vehicules/{id}")
    public ResponseEntity<Vehicule> updateVehicule(
            @PathVariable(value = "id") Long vehiculeId, @Valid @RequestBody Vehicule vehiculeDetails)
            throws ResourceNotFoundException {
        Vehicule vehicule =
                vehiculeRepository
                        .findById(vehiculeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Vehicule not found on : " + vehiculeId));
        vehicule.setCoordinates(vehiculeDetails.getCoordinates());
        vehicule.setUpdatedAt(new Date());
        final Vehicule updateVehicule = vehiculeRepository.save(vehicule);
        return ResponseEntity.ok(updateVehicule);
    }

    /**
     * delete a vehicule
     * @param vehiculeId
     * @return Boolean
     * @throws Exception
     */
    @DeleteMapping("/vehicules/{id}")
    public Map<String, Boolean> deleteVehicule(@PathVariable(value = "id") Long vehiculeId) throws Exception {
        Vehicule vehicule =
                vehiculeRepository
                        .findById(vehiculeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Vehicule not found on : " + vehiculeId));
        vehiculeRepository.delete(vehicule);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
