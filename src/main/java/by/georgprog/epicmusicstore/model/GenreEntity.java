package by.georgprog.epicmusicstore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
@Data
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @ManyToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private List<TrackEntity> trackList;
}
