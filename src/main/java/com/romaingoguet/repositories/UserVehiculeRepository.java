package com.romaingoguet.repositories;

import com.romaingoguet.models.UserVehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVehiculeRepository extends JpaRepository<UserVehicule, Long> {
}
