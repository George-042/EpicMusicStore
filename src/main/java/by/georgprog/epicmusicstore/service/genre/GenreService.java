package by.georgprog.epicmusicstore.service.genre;

import by.georgprog.epicmusicstore.dto.genre.CreateUpdateGenreRequest;
import by.georgprog.epicmusicstore.dto.genre.GenreDto;
import by.georgprog.epicmusicstore.exeption.badrequest.GenreNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.AccessDeniedException;

import java.util.List;

public interface GenreService {

    GenreDto findGenre(Long id) throws GenreNotFoundException;

    List<GenreDto> findAllGenres();

    void createGenre(CreateUpdateGenreRequest genreDto) throws AccessDeniedException;

    void updateGenre(Long id, CreateUpdateGenreRequest genreDto) throws AccessDeniedException, GenreNotFoundException;

    void deleteGenre(Long id) throws AccessDeniedException, GenreNotFoundException;
}
