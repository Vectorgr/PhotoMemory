package net.flashbacks.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
@EnableAutoConfiguration
@Entity
@Table(name="images")
public class ImageModel {
    public ImageModel(String image_name, String image_path, byte[] image_data) throws SQLException {
        this.image_name = image_name;
        this.image_path = image_path;
        this.image_data = new SerialBlob(image_data );

    }
    public ImageModel(){

    }
    @Id
    private UUID id = UUID.randomUUID();
    @Column
    private UUID parent_memory;
    @Column
    private String image_name;

    @Column
    private String image_path;

    @Column
    private Blob image_data;

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getImage_name() {
        return image_name;
    }
    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public byte[] getImage_data() throws SQLException {
        return image_data.getBytes(1L, (int) image_data.length());
    }

    public void setImage_data(byte[] image_data) throws SQLException {
        this.image_data = new SerialBlob(image_data);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getParent_memory() {
        return parent_memory;
    }

    public void setParent_memory(UUID parent_memory) {
        this.parent_memory = parent_memory;
    }
}
