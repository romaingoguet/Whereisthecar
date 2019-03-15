package com.romaingoguet.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "user_vehicule")
@IdClass(UserVehicule.IdClass.class)
public class UserVehicule implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "vehicule")
    private Vehicule vehicule;

    @JoinColumn(name = "is_owner")
    private Boolean isOwner;

    public UserVehicule() {
    }

    public UserVehicule(User user, Vehicule vehicule) {
        this.user = user;
        this.vehicule = vehicule;
    }

    public UserVehicule(User user, Vehicule vehicule, Boolean isOwner) {
        this.user = user;
        this.vehicule = vehicule;
        this.isOwner = isOwner;
    }

    static class IdClass implements Serializable{
        private Long user;
        private Long vehicule;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Boolean getOwner() {
        return isOwner;
    }

    public void setOwner(Boolean owner) {
        isOwner = owner;
    }
}
