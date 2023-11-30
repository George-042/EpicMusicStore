package by.georgprog.epicmusicstore.repo;

import by.georgprog.epicmusicstore.model.TrackEntity;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TrackRepo")
public interface TrackRepository extends JpaRepository<TrackEntity, Long> {

    @Query("SELECT t FROM TrackEntity t WHERE :authors MEMBER OF t.authors")
    List<TrackEntity> findAllTracksByAuthor(@Param("authors") List<UserEntity> authors);
}
