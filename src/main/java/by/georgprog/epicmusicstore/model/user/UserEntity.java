package by.georgprog.epicmusicstore.model.user;

import by.georgprog.epicmusicstore.model.AlbumEntity;
import by.georgprog.epicmusicstore.model.PlaylistEntity;
import by.georgprog.epicmusicstore.model.TrackEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 200)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 200)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "gender", nullable = false, length = 6)
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @Column(name = "date_of_birth", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PlaylistEntity> playlists;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<AlbumEntity> albums;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "musician_track_list",
            joinColumns = @JoinColumn(name = "musician_id", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "track_id", nullable = false, updatable = false)
    )
    private List<TrackEntity> trackList;

    @Column(name = "user_picture", columnDefinition = "BYTEA")
    private Byte[] userPic;

    @Column(name = "role", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "is_confirmed", nullable = false)
    @Accessors(fluent = true)
    private Boolean isConfirmed;
}
