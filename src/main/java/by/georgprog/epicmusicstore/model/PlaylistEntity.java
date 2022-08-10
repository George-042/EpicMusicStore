package by.georgprog.epicmusicstore.model;

import by.georgprog.epicmusicstore.model.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Playlist")
@Table(name = "playlists")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PlaylistEntity {

    @Id
    @SequenceGenerator(
            name = "playlists_sequence",
            sequenceName = "playlists_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "playlists_sequence"
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "playlist_tracks",
            joinColumns = @JoinColumn(name = "playlist_id", nullable = false, updatable = false, insertable = false),
            inverseJoinColumns = @JoinColumn(name = "track_id", nullable = false, updatable = false, insertable = false)
    )
    private List<TrackEntity> trackList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "owner_id",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private UserEntity owner;

    @Column(
            name = "playlist_picture",
            columnDefinition = "BYTEA"
    )
    private Byte[] playlistPic;
}
