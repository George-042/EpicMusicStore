package by.georgprog.epicmusicstore.model;

import by.georgprog.epicmusicstore.model.user.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "albums")
@Data
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
    private List<TrackEntity> trackList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musician_id", nullable = false, updatable = false)
    private UserEntity owner;

    @Lob
    @Column(name = "album_picture", columnDefinition = "BYTEA")
    private byte[] albumPic;
}
