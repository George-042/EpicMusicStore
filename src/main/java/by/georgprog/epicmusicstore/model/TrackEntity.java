package by.georgprog.epicmusicstore.model;

import by.georgprog.epicmusicstore.model.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tracks")
@Data
public class TrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(mappedBy = "trackList", fetch = FetchType.LAZY)
    private List<UserEntity> author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "track_genre",
            joinColumns = @JoinColumn(name = "track_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "genre_id", nullable = false, updatable = false)
    )
    private List<GenreEntity> genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false, updatable = false)
    private AlbumEntity album;

    @ManyToMany(mappedBy = "trackList", fetch = FetchType.LAZY)
    private List<PlaylistEntity> inPlaylists;

    @Column(name = "publication_date", nullable = false, columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm")
    private Date publicationDate;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "track_picture", columnDefinition = "BYTEA")
    private byte[] trackPic;

    @Column(name = "file", nullable = false, columnDefinition = "BYTEA")
    @Basic(fetch = FetchType.LAZY)
    private byte[] file;

}
