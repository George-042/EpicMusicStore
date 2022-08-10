package by.georgprog.epicmusicstore.model.user;

import by.georgprog.epicmusicstore.model.AlbumEntity;
import by.georgprog.epicmusicstore.model.PlaylistEntity;
import by.georgprog.epicmusicstore.model.TrackEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "User")
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserEntity {

    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            unique = true,
            columnDefinition = "TEXT",
            length = 200
    )
    private String name;

    @Column(
            name = "email",
            nullable = false,
            unique = true,
            columnDefinition = "TEXT",
            length = 200
    )
    private String email;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @Column(
            name = "gender",
            nullable = false,
            columnDefinition = "TEXT",
            length = 6
    )
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @Column(
            name = "date_of_birth",
            nullable = false,
            columnDefinition = "DATE"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PlaylistEntity> playlists;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<AlbumEntity> albums;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "musician_track_list",
            joinColumns = @JoinColumn(name = "musician_id", nullable = false, updatable = false, insertable = false),
            inverseJoinColumns = @JoinColumn(name = "track_id", nullable = false, updatable = false, insertable = false)
    )
    private List<TrackEntity> trackList;

    @Column(
            name = "user_picture",
            columnDefinition = "BYTEA"
    )
    private Byte[] userPic;

    @Column(
            name = "role",
            nullable = false,
            columnDefinition = "TEXT",
            length = 10
    )
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(
            name = "is_confirmed",
            nullable = false
    )
    private Boolean isConfirmed = false;
}
