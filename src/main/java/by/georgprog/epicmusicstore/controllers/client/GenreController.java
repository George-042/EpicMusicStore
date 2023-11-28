package by.georgprog.epicmusicstore.controllers.client;

import by.georgprog.epicmusicstore.dto.genre.CreateUpdateGenreRequest;
import by.georgprog.epicmusicstore.dto.genre.GenreDto;
import by.georgprog.epicmusicstore.exeption.badrequest.GenreNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.AccessDeniedException;
import by.georgprog.epicmusicstore.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAllGenres() {
        return new ResponseEntity<>(genreService.findAllGenres(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenre(@PathVariable Long id) throws GenreNotFoundException {
        return new ResponseEntity<>(genreService.findGenre(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createGenre(CreateUpdateGenreRequest genreDto) throws AccessDeniedException {
        genreService.createGenre(genreDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGenre(@PathVariable Long id, CreateUpdateGenreRequest genreDto)
            throws AccessDeniedException, GenreNotFoundException {
        genreService.updateGenre(id, genreDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable Long id) throws AccessDeniedException,
            GenreNotFoundException {
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
