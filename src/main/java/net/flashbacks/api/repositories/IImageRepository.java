package net.flashbacks.api.repositories;

import net.flashbacks.api.models.ImageModel;
import net.flashbacks.api.models.MemoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IImageRepository extends JpaRepository<ImageModel, UUID> {
    List<ImageModel> findByParentMemory(UUID parentMemory);

}
