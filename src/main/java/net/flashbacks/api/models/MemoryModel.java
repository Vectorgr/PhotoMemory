package net.flashbacks.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
@EnableAutoConfiguration
@Entity
@Table(name="memories")
public class MemoryModel {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdDate = LocalDate.now();

    private List<UUID> images;

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
    public List<UUID> getImages(){

        return images;
    }
    public void addImageModel(ImageModel imageModel){
        this.images.add(imageModel.getId());
    }
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setImages(List<UUID> images) {
        this.images = images;
    }
}
