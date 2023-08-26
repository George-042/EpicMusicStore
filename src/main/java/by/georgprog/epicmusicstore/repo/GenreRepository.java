package by.georgprog.epicmusicstore.repo;

import by.georgprog.epicmusicstore.model.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("GenreRepo")
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
}
