package by.georgprog.epicmusicstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Genre")
@Table(name = "genres")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GenreEntity {

    @Id
    @SequenceGenerator(
            name = "genres_sequence",
            sequenceName = "genres_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "genres_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT",
            length = 100
    )
    private String name;

    @ManyToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    private List<TrackEntity> trackList;
}
