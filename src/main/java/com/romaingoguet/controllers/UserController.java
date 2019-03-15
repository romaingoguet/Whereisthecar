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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehiculeRepository vehiculeRepository;

    /**
     * get all users
     * @return User list
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * get a user
     * @param userId
     * @return User
     * @throws ResourceNotFoundException
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getVehiculesById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on : " + userId));
        return ResponseEntity.ok().body(user);
    }

    /**
     * get users list associated with a vehicule
     * @param vehiculeId
     * @return User List
     * @throws ResourceNotFoundException
     */
    @GetMapping("/vehicules/{id}/users")
    public List<User> getUsersByVehiculesId(@PathVariable(value = "id") Long vehiculeId) throws ResourceNotFoundException {
        vehiculeRepository.findById(vehiculeId).orElseThrow(() -> new ResourceNotFoundException("Vehicule : " + vehiculeId + " not found"));
        return userRepository.findByVehiculeId(vehiculeId);
    }

    /**
     * add a user
     * @param user
     * @return User
     */
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    /**
     * update user informations
     * @param userId
     * @param userDetails
     * @return  User
     * @throws ResourceNotFoundException
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
            throws ResourceNotFoundException {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on : " + userId));
        user.setName(userDetails.getName());
        final User updateUser = userRepository.save(user);
        return ResponseEntity.ok(updateUser);
    }

    /**
     * delete a user
     * @param userId
     * @return Boolean
     * @throws Exception
     */
    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on : " + userId));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
