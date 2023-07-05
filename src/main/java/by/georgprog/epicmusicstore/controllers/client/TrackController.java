package by.georgprog.epicmusicstore.controllers.client;

import by.georgprog.epicmusicstore.dto.track.CreateUpdateTrackRequest;
import by.georgprog.epicmusicstore.exeption.badrequest.UserNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import by.georgprog.epicmusicstore.service.track.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

@RestController
@RequestMapping("users/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @PostMapping
    public ResponseEntity<String> createTrack(@RequestBody CreateUpdateTrackRequest trackDto)
            throws UnsupportedAudioFileException, IOException, UserNotFoundException {
        trackService.createTrack(trackDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{trackId}")
    public ResponseEntity<String> updateTrack(@PathVariable Long trackId,
                                              @RequestBody CreateUpdateTrackRequest trackDto)
            throws UserNotFoundException, UnsupportedAudioFileException, IOException, ObtainingDataException {
        trackService.updateTrack(trackId, trackDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
