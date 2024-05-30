package net.flashbacks.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.sql.rowset.serial.SerialBlob;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

@EnableAutoConfiguration
@Entity
@Table(name="locations")
public class LocationModel {
    @Id
    private UUID id = UUID.randomUUID();

    @Column
    private String name;

    @Column
    private String description;
    @Column(columnDefinition="LONGBLOB")
    private Blob image;

    @Column(precision=10, scale=7)
    private BigDecimal latitude;
    @Column(precision=10, scale=7)
    private BigDecimal longitude;
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdDate = LocalDate.now();

    public byte[] getImage() throws SQLException {
        if(image==null)
            return null;
        return image.getBytes(1L, (int) image.length());
    }

    public void setImage(byte[] image_data)  {
        try {
            this.image = new SerialBlob(image_data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
