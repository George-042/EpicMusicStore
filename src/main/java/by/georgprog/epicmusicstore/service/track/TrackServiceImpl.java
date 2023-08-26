package by.georgprog.epicmusicstore.service.track;

import by.georgprog.epicmusicstore.dto.track.CreateUpdateTrackRequest;
import by.georgprog.epicmusicstore.dto.track.TrackDto;
import by.georgprog.epicmusicstore.exeption.badrequest.TrackNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.UserNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import by.georgprog.epicmusicstore.mapper.TrackMapper;
import by.georgprog.epicmusicstore.model.TrackEntity;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import by.georgprog.epicmusicstore.repo.AlbumRepository;
import by.georgprog.epicmusicstore.repo.GenreRepository;
import by.georgprog.epicmusicstore.repo.TrackRepository;
import by.georgprog.epicmusicstore.repo.UserRepository;
import by.georgprog.epicmusicstore.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    private final TrackMapper trackMapper;
    private final TrackRepository trackRepository;
    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    @Override
    public TrackDto findOwnerTrack(Long id) throws TrackNotFoundException, ObtainingDataException, UserNotFoundException {
        TrackEntity trackEntity = trackRepository.findById(id).orElseThrow(TrackNotFoundException::new);
        UserEntity userEntity = userRepository.findById(SecurityContextUtils.getUserFromSecurityContext().getId())
                .orElseThrow(UserNotFoundException::new);
        if (!trackEntity.getAuthor().contains(userEntity)) {
            throw new ObtainingDataException("You can not access the track");
        }
        return trackMapper.toDto(trackEntity);
    }

    @Override
    public List<TrackDto> findAllOwnerTracks() throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(SecurityContextUtils.getUserFromSecurityContext().getId())
                .orElseThrow(UserNotFoundException::new);
        return trackRepository.findAllTracksByAuthor(Collections.singletonList(userEntity))
                .stream().map(trackMapper::toDto).toList();
    }

    @Override
    public void createTrack(CreateUpdateTrackRequest trackDto) throws UnsupportedAudioFileException, IOException,
            UserNotFoundException {
        TrackEntity trackEntity = fillInTrackEntity(trackDto);
        trackRepository.save(trackEntity);
    }

    @Override
    public void updateTrack(Long id, CreateUpdateTrackRequest trackDto) throws UserNotFoundException,
            UnsupportedAudioFileException, IOException, ObtainingDataException {
        TrackEntity trackEntity = fillInTrackEntity(trackDto);
        if (!trackEntity.getAuthor().contains(userRepository.findById(SecurityContextUtils.getUserFromSecurityContext()
                .getId()).orElseThrow(UserNotFoundException::new))) {
            throw new ObtainingDataException("You can not access the track");
        }
        trackEntity.setId(id);
        trackRepository.save(trackEntity);
    }

    private TrackEntity fillInTrackEntity(CreateUpdateTrackRequest trackDto) throws UserNotFoundException,
            UnsupportedAudioFileException, IOException {
        TrackEntity trackEntity = trackMapper.toEntity(trackDto);
        List<UserEntity> authors = trackEntity.getAuthor();
        authors.add(userRepository.findById(SecurityContextUtils.getUserFromSecurityContext().getId())
                .orElseThrow(UserNotFoundException::new));
        for (Long userId : trackDto.getAuthorsId()) {
            userRepository.findById(userId).ifPresent(authors::add);
        }
        for (Long genreId : trackDto.getListGenreId()) {
            genreRepository.findById(genreId).ifPresent(genre -> trackEntity.getGenre().add(genre));
        }
        albumRepository.findById(trackDto.getAlbumId()).ifPresent(trackEntity::setAlbum);
        AudioInputStream audioInputStream =
                AudioSystem.getAudioInputStream(new ByteArrayInputStream(trackEntity.getFile()));
        AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(audioInputStream);
        trackEntity.setDuration(audioFileFormat.getFrameLength());
        trackEntity.setAuthor(authors);
        trackEntity.setPublicationDate(Date.from(Instant.from(LocalDateTime.now())));
        return trackEntity;
    }
}
