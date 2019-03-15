package com.romaingoguet.repositories;

import com.romaingoguet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "Select * from users as u join user_vehicule as uv where uv.vehicule = ?1", nativeQuery = true)
    List<User> findByVehiculeId(Long id);
}
