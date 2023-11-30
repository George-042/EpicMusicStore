package by.georgprog.epicmusicstore.repo;

import by.georgprog.epicmusicstore.model.PlaylistEntity;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PlaylistRepo")
public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {

    List<PlaylistEntity> findAllByOwner(UserEntity userEntity);
}
