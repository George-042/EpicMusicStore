package by.georgprog.epicmusicstore.repo;

import by.georgprog.epicmusicstore.model.AlbumEntity;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("AlbumRepo")
public interface AlbumRepository extends JpaRepository<AlbumEntity, Long> {

    List<AlbumEntity> findAllByOwner(UserEntity owner);

    Optional<AlbumEntity> findByIdAndOwner(Long id, UserEntity owner);
}
