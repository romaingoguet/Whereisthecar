package com.romaingoguet.repositories;

import com.romaingoguet.models.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    @Query(value = "SELECT * FROM vehicules AS v JOIN user_vehicule AS uv ON uv.vehicule = v.id WHERE uv.user = ?1", nativeQuery = true)
    List<Vehicule> findByUserId(Long id);

}
