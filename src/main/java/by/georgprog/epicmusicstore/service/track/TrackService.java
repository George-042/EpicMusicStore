package by.georgprog.epicmusicstore.service.track;

import by.georgprog.epicmusicstore.dto.track.CreateUpdateTrackRequest;
import by.georgprog.epicmusicstore.dto.track.TrackDto;
import by.georgprog.epicmusicstore.exeption.badrequest.TrackNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.UserNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;

public interface TrackService {

    TrackDto findOwnerTrack(Long id) throws TrackNotFoundException, UserNotFoundException, ObtainingDataException;

    List<TrackDto> findAllOwnerTracks() throws UserNotFoundException;

    void createTrack(CreateUpdateTrackRequest trackDto) throws UnsupportedAudioFileException, IOException,
            UserNotFoundException;

    void updateTrack(Long id, CreateUpdateTrackRequest trackDto) throws UserNotFoundException,
            UnsupportedAudioFileException, IOException, ObtainingDataException;
}
