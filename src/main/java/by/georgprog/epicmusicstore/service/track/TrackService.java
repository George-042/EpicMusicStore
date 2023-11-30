package by.georgprog.epicmusicstore.service.track;

import by.georgprog.epicmusicstore.dto.track.CreateUpdateTrackRequest;
import by.georgprog.epicmusicstore.dto.track.TrackDto;
import by.georgprog.epicmusicstore.exeption.badrequest.AlbumNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.TrackNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.UserNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TrackService {

    TrackDto findTrack(Long id) throws TrackNotFoundException;

    List<TrackDto> findAllTracks();

    List<TrackDto> findAllOwnerTracks(Long id) throws UserNotFoundException;

    void createTrack(CreateUpdateTrackRequest trackDto, MultipartFile file, MultipartFile trackImg)
            throws IOException, UserNotFoundException, AlbumNotFoundException, CannotReadException, TagException,
            InvalidAudioFrameException, ReadOnlyFileException;

    void updateTrack(Long id, CreateUpdateTrackRequest trackDto, MultipartFile file, MultipartFile trackImg)
            throws UserNotFoundException, IOException, ObtainingDataException,
            AlbumNotFoundException, TrackNotFoundException, CannotReadException, TagException,
            InvalidAudioFrameException, ReadOnlyFileException;

    void deleteTrack(Long id);
}
