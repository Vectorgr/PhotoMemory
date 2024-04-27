package net.flashbacks.api.repositories;

import net.flashbacks.api.models.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPersonRepository extends JpaRepository<PersonModel, UUID> {
}
