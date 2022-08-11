package by.georgprog.epicmusicstore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
@Data
public class GenreEntity {

    @Id
    @SequenceGenerator(name = "genres_sequence", sequenceName = "genres_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "genres_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private List<TrackEntity> trackList;
}
