package by.georgprog.epicmusicstore.model;

import by.georgprog.epicmusicstore.model.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "Track")
@Table(name = "tracks")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrackEntity {

    @Id
    @SequenceGenerator(
            name = "tracks_sequence",
            sequenceName = "tracks_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tracks_sequence"
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

    @ManyToMany(mappedBy = "trackList", fetch = FetchType.LAZY)
    private List<UserEntity> author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "track_genre",
            joinColumns = @JoinColumn(name = "track_id", nullable = false, updatable = false, insertable = false),
            inverseJoinColumns = @JoinColumn(name = "genre_id", nullable = false, updatable = false, insertable = false)
    )
    private List<GenreEntity> genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "album_id",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private AlbumEntity album;

    @ManyToMany(mappedBy = "trackList", fetch = FetchType.LAZY)
    private List<PlaylistEntity> inPlaylists;

    @Column(
            name = "publication_date",
            nullable = false,
            columnDefinition = "DATE"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm")
    private Date publicationDate;

    @Column(
            name = "duration",
            nullable = false,
            columnDefinition = "INTEGER"
    )
    private Integer duration;

    @Column(
            name = "track_picture",
            columnDefinition = "BYTEA"
    )
    private Byte[] trackPic;

    @Column(
            name = "file",
            nullable = false,
            columnDefinition = "BYTEA"
    )
    private Byte[] file;

}
