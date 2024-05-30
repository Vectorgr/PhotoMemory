package net.flashbacks.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.UUID;

@EnableAutoConfiguration
@Entity
@Table(name="users")
public class UserModel {
    @Id
    private UUID id = UUID.randomUUID();
    @Column
    private String name;
    @Column
    private String hashPass;

    @Column
    private boolean admin;
    public UUID getId(){
        return id;
    }
    public void setId(UUID id){
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashPass() {
        return hashPass;
    }

    public void setHashPass(String hashPass) {
        this.hashPass = hashPass;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public UserModel(String name, String hashPass, boolean admin) {
        this.name = name;
        this.hashPass = hashPass;
        this.admin = admin;
    }
    public  UserModel(){

    }
}