package net.flashbacks.api.repositories;

import net.flashbacks.api.models.ImageModel;
import net.flashbacks.api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByName(String name);
}
