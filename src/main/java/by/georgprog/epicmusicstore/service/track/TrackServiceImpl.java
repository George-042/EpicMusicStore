package by.georgprog.epicmusicstore.service.track;

import by.georgprog.epicmusicstore.dto.track.CreateUpdateTrackRequest;
import by.georgprog.epicmusicstore.dto.track.TrackDto;
import by.georgprog.epicmusicstore.exeption.badrequest.AlbumNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.GenreNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.TrackNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.UserNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import by.georgprog.epicmusicstore.mapper.TrackMapper;
import by.georgprog.epicmusicstore.model.GenreEntity;
import by.georgprog.epicmusicstore.model.TrackEntity;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import by.georgprog.epicmusicstore.repo.AlbumRepository;
import by.georgprog.epicmusicstore.repo.GenreRepository;
import by.georgprog.epicmusicstore.repo.TrackRepository;
import by.georgprog.epicmusicstore.repo.UserRepository;
import by.georgprog.epicmusicstore.utils.AudioFileUtils;
import by.georgprog.epicmusicstore.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    private final TrackMapper trackMapper;
    private final TrackRepository trackRepository;
    private final GenreRepository genreRepository;
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    @Override
    public TrackDto findTrack(Long id) throws TrackNotFoundException {
        TrackEntity trackEntity = trackRepository.findById(id).orElseThrow(TrackNotFoundException::new);
        TrackDto dto = trackMapper.toDto(trackEntity);
        dto.setFile(trackEntity.getFile());
        return dto;
    }

    @Override
    public List<TrackDto> findAllTracks() {
        return trackRepository.findAll().stream().map(trackEntity -> {
            TrackDto dto = trackMapper.toDto(trackEntity);
            dto.setFile(trackEntity.getFile());
            return dto;
        }).toList();
    }

    @Override
    public List<TrackDto> findAllOwnerTracks(Long id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return trackRepository.findAllTracksByAuthor(Collections.singletonList(userEntity))
                .stream().map(trackEntity -> {
                    TrackDto dto = trackMapper.toDto(trackEntity);
                    dto.setFile(trackEntity.getFile());
                    return dto;
                }).toList();
    }

    @Override
    public void createTrack(CreateUpdateTrackRequest trackDto, MultipartFile file, MultipartFile trackImg)
            throws IOException, UserNotFoundException, AlbumNotFoundException,
            CannotReadException, TagException, InvalidAudioFrameException, ReadOnlyFileException {
        TrackEntity trackEntity = fillInTrackEntity(trackDto, file, trackImg);
        trackRepository.save(trackEntity);
    }

    @Override
    public void updateTrack(Long id, CreateUpdateTrackRequest trackDto, MultipartFile file, MultipartFile trackImg)
            throws UserNotFoundException, IOException, ObtainingDataException, AlbumNotFoundException,
            TrackNotFoundException, CannotReadException, TagException, InvalidAudioFrameException,
            ReadOnlyFileException {
        TrackEntity trackEntityFromDb = trackRepository.findById(id).orElseThrow(TrackNotFoundException::new);
        if (!trackEntityFromDb.getAuthors()
                .contains(userRepository.findById(SecurityContextUtils.getUserFromSecurityContext().getId())
                        .orElseThrow(UserNotFoundException::new))) {
            throw new ObtainingDataException("You can not access the track");
        }

        TrackEntity trackEntity = fillInTrackEntity(trackDto, file, trackImg);
        trackEntity.setId(id);
        trackRepository.save(trackEntity);
    }

    @Override
    public void deleteTrack(Long id) {
        trackRepository.deleteById(id);
    }

    public TrackEntity fillInTrackEntity(CreateUpdateTrackRequest trackDto, MultipartFile file, MultipartFile trackImg)
            throws UserNotFoundException, IOException, AlbumNotFoundException, CannotReadException, TagException,
            InvalidAudioFrameException, ReadOnlyFileException {
        TrackEntity trackEntity = new TrackEntity();

        List<UserEntity> authors = new ArrayList<>();
        List<GenreEntity> genres = new ArrayList<>();
        UserEntity userEntity = userRepository.findById(SecurityContextUtils.getUserFromSecurityContext().getId())
                .orElseThrow(UserNotFoundException::new);
        authors.add(userEntity);
        for (Long userId : trackDto.getAuthorsId()) {
            userRepository.findById(userId).ifPresentOrElse(authors::add, UserNotFoundException::new);
        }
        for (Long genreId : trackDto.getGenresId()) {
            genreRepository.findById(genreId).ifPresentOrElse(genres::add, GenreNotFoundException::new);
        }

        trackEntity.setName(trackDto.getName());
        trackEntity.setAuthors(authors);
        trackEntity.setGenres(genres);
        trackEntity.setAlbum(albumRepository.findById(trackDto.getAlbumId()).orElseThrow(AlbumNotFoundException::new));
        trackEntity.setPublicationDate(Date.from(Instant.now()));
        trackEntity.setDuration(AudioFileUtils.getAudioHeader(AudioFileUtils.getAudioFile(file)).getTrackLength());
        trackEntity.setFile(file.getBytes());
        if (trackImg != null) {
            trackEntity.setTrackPic(trackImg.getBytes());
        }
        return trackEntity;
    }
}
