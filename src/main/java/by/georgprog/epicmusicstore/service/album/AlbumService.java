package by.georgprog.epicmusicstore.service.album;

import by.georgprog.epicmusicstore.dto.AlbumDto;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import by.georgprog.epicmusicstore.exeption.unauthorized.AlbumNotFoundException;
import by.georgprog.epicmusicstore.exeption.unauthorized.UserNotFoundException;

import java.util.List;

public interface AlbumService {

    AlbumDto findOwnerAlbum(Long id) throws AlbumNotFoundException, ObtainingDataException;

    List<AlbumDto> findAllOwnerAlbums() throws UserNotFoundException;

    void createAlbum(AlbumDto albumDto) throws UserNotFoundException;

    void updateAlbum(Long id, AlbumDto albumDto) throws AlbumNotFoundException;

    void deleteAlbum(Long id) throws AlbumNotFoundException;

    void uploadImage(Long id, byte[] image) throws AlbumNotFoundException;
}
