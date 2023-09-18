package io.javabrains.springsecurityjpa;

import io.javabrains.springsecurityjpa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// You can avoid using the controller and start using SPRING DATA REST BY ADDING BELOW LINE:
// @RepositoryRestResource(collectionResourceRel="users",path="users")
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}
