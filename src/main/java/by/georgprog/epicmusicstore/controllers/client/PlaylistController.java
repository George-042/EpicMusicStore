package by.georgprog.epicmusicstore.controllers.client;

import by.georgprog.epicmusicstore.dto.playlist.CreateUpdatePlaylistRequest;
import by.georgprog.epicmusicstore.dto.playlist.PlaylistDto;
import by.georgprog.epicmusicstore.exeption.badrequest.PlaylistNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.TrackNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.UserNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import by.georgprog.epicmusicstore.service.playlist.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<List<PlaylistDto>> getAllPlaylists() {
        return new ResponseEntity<>(playlistService.findAllPlaylists(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<PlaylistDto>> getAllOwnerPlaylists(@PathVariable Long id) throws UserNotFoundException {
        return new ResponseEntity<>(playlistService.findAllOwnerPlaylists(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDto> getPlaylist(@PathVariable Long id) throws PlaylistNotFoundException {
        return new ResponseEntity<>(playlistService.findPlaylist(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createPlaylist(@ModelAttribute CreateUpdatePlaylistRequest playlistDto,
                                                 @RequestPart(value = "img", required = false) MultipartFile img)
            throws UserNotFoundException, TrackNotFoundException, IOException {
        playlistService.createPlaylist(playlistDto, img);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlaylist(@PathVariable Long id,
                                                 @ModelAttribute CreateUpdatePlaylistRequest playlistDto,
                                                 @RequestPart(value = "img", required = false) MultipartFile img)
            throws UserNotFoundException, TrackNotFoundException, PlaylistNotFoundException, IOException,
            ObtainingDataException {
        playlistService.updatePlaylist(id, playlistDto, img);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
