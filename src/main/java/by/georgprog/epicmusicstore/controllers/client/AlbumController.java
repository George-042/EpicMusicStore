package by.georgprog.epicmusicstore.controllers.client;

import by.georgprog.epicmusicstore.dto.AlbumDto;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
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
    public ResponseEntity<List<AlbumDto>> getAlbumsWithPicture() throws UserNotFoundException {
        return new ResponseEntity<>(albumService.findAllOwnerAlbums(), HttpStatus.OK);
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<AlbumDto> getAlbum(@PathVariable Long albumId) throws AlbumNotFoundException,
            ObtainingDataException {
        return new ResponseEntity<>(albumService.findOwnerAlbum(albumId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createAlbum(@RequestBody AlbumDto albumDto) throws UserNotFoundException {
        albumService.createAlbum(albumDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{albumId}")
    public ResponseEntity<String> updateAlbum(@PathVariable Long id, @RequestBody AlbumDto albumDto)
            throws AlbumNotFoundException {
        albumService.updateAlbum(id, albumDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long albumId)
            throws AlbumNotFoundException {
        albumService.deleteAlbum(albumId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{albumId}/picture")
    public ResponseEntity<String> uploadPicture(@PathVariable("albumId") Long id,
                                                @RequestParam("file") MultipartFile file) throws IOException,
            AlbumNotFoundException {
        albumService.uploadImage(id, file.getBytes());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
