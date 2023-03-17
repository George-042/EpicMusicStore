package by.georgprog.epicmusicstore.repo;

import by.georgprog.epicmusicstore.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserRepo")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByActivationCode(String activationCode);

    Optional<UserEntity> findByEmail(String email);
}
