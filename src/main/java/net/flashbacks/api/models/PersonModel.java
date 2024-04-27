package net.flashbacks.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
@EnableAutoConfiguration
@Entity
@Table(name="people")
public class PersonModel {
    @Id
    private UUID id = UUID.randomUUID();

    @Column
    private String name;

    @Column
    private String biography;
    @Column
    private Blob profilePic;
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdDate = LocalDate.now();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public byte[] getProfilePic() throws SQLException {
        if(profilePic==null)
            return null;
        return profilePic.getBytes(1L, (int) profilePic.length());
    }

    public void setProfilePic(byte[] image_data)  {
        try {
            this.profilePic = new SerialBlob(image_data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
