package by.georgprog.epicmusicstore.model;

import by.georgprog.epicmusicstore.model.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Album")
@Table(name = "albums")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AlbumEntity {

    @Id
    @SequenceGenerator(
            name = "albums_sequence",
            sequenceName = "albums_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "albums_sequence"
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
            length = 150
    )
    private String name;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
    private List<TrackEntity> trackList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "musician_id",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private UserEntity owner;

    @Column(
            name = "album_picture",
            columnDefinition = "BYTEA"
    )
    private Byte[] albumPic;
}
