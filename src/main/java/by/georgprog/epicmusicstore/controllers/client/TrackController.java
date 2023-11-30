package by.georgprog.epicmusicstore.controllers.client;

import by.georgprog.epicmusicstore.dto.track.CreateUpdateTrackRequest;
import by.georgprog.epicmusicstore.dto.track.TrackDto;
import by.georgprog.epicmusicstore.exeption.badrequest.AlbumNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.TrackNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.UserNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import by.georgprog.epicmusicstore.service.track.TrackService;
import lombok.RequiredArgsConstructor;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @GetMapping
    public ResponseEntity<List<TrackDto>> getAllTracks() {
        return new ResponseEntity<>(trackService.findAllTracks(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<TrackDto>> getAllOwnerTracks(@PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(trackService.findAllOwnerTracks(id), HttpStatus.OK);
    }

    @GetMapping("/{trackId}")
    public ResponseEntity<TrackDto> getTrack(@PathVariable Long trackId) throws TrackNotFoundException {
        return new ResponseEntity<>(trackService.findTrack(trackId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createTrack(@ModelAttribute CreateUpdateTrackRequest trackDto,
                                              @RequestPart("file") MultipartFile file,
                                              @RequestPart(value = "trackImg", required = false) MultipartFile trackImg)
            throws IOException, UserNotFoundException, AlbumNotFoundException, CannotReadException, TagException,
            InvalidAudioFrameException, ReadOnlyFileException {
        trackService.createTrack(trackDto, file, trackImg);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{trackId}")
    public ResponseEntity<String> updateTrack(@PathVariable Long trackId,
                                              @ModelAttribute CreateUpdateTrackRequest trackDto,
                                              @RequestPart("file") MultipartFile file,
                                              @RequestPart(value = "trackImg", required = false) MultipartFile trackImg)
            throws UserNotFoundException, IOException, ObtainingDataException, TrackNotFoundException,
            AlbumNotFoundException, CannotReadException, TagException, InvalidAudioFrameException,
            ReadOnlyFileException {
        trackService.updateTrack(trackId, trackDto, file, trackImg);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{trackId}")
    public ResponseEntity<String> deleteTrack(@PathVariable Long trackId) {
        trackService.deleteTrack(trackId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
