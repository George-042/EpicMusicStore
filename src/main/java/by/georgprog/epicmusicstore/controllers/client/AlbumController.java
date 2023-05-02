package by.georgprog.epicmusicstore.controllers.client;

import by.georgprog.epicmusicstore.dto.AlbumDto;
import by.georgprog.epicmusicstore.exeption.unauthorized.AlbumNotFoundException;
import by.georgprog.epicmusicstore.exeption.unauthorized.UserNotFoundException;
import by.georgprog.epicmusicstore.service.album.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("users/{userId}/albums")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<AlbumDto>> getAlbumsWithPicture(@PathVariable Long userId) throws UserNotFoundException {
        return new ResponseEntity<>(albumService.getUserAlbums(userId), HttpStatus.OK);
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumDto> getAlbum(@PathVariable Long userId, @PathVariable Long albumId)
            throws AlbumNotFoundException, UserNotFoundException {
        return new ResponseEntity<>(albumService.getAlbum(userId, albumId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createAlbum(@PathVariable Long userId, @RequestBody AlbumDto albumDto) {
        albumService.createAlbum(userId, albumDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{albumId}")
    public ResponseEntity<String> updateAlbum(@PathVariable Long userId, @PathVariable Long albumId,
                                              @RequestBody AlbumDto albumDto) throws AlbumNotFoundException,
            UserNotFoundException {
        albumService.updateAlbum(userId, albumId, albumDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long userId, @PathVariable Long albumId)
            throws UserNotFoundException, AlbumNotFoundException {
        albumService.deleteAlbum(userId, albumId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{albumId}/picture")
    public ResponseEntity<String> uploadPicture(@PathVariable Long userId, @PathVariable("albumId") Long albumId,
                                                @RequestParam("file") MultipartFile file)
            throws IOException, AlbumNotFoundException {
        albumService.uploadImage(userId, albumId, file.getBytes());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
