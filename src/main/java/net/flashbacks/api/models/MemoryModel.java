package net.flashbacks.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.time.LocalDate;
import java.util.UUID;
@EnableAutoConfiguration
@Entity
@Table(name="memories")
public class MemoryModel {
    @Id
    private UUID id = UUID.randomUUID();

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private byte[] image;
    @Column
    private String imagePath;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setImage(byte[] image){this.image = image;}
    public byte[] getImage(){return this.image;}

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    public String getImagePath(){
        return this.imagePath;
    }
}
