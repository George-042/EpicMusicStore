package by.georgprog.epicmusicstore.model;

import by.georgprog.epicmusicstore.model.user.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "playlists")
@Data
public class PlaylistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "playlist_tracks",
            joinColumns = @JoinColumn(name = "playlist_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "track_id", nullable = false, updatable = false)
    )
    private List<TrackEntity> trackList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false, updatable = false)
    private UserEntity owner;

    @Column(name = "playlist_picture", columnDefinition = "BYTEA")
    private Byte[] playlistPic;
}
