package com.romaingoguet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vehicules")
public class Vehicule extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double[] coordinates;

    @JsonIgnore
    private double latitude;

    @JsonIgnore
    private double longitude;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<UserVehicule> users = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double[] getCoordinates() {
        return new double[]{latitude, longitude};
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setCoordinates(double[] coordinates) {
        this.latitude = coordinates[1];
        this.longitude = coordinates[0];
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Set<UserVehicule> getUsers() {
        return users;
    }

    public void setUsers(Set<UserVehicule> users) {
        this.users = users;
    }
}
