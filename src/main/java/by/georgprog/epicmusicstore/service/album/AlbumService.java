package by.georgprog.epicmusicstore.service.album;

import by.georgprog.epicmusicstore.dto.album.AlbumDto;
import by.georgprog.epicmusicstore.dto.album.CreateUpdateAlbumRequest;
import by.georgprog.epicmusicstore.exeption.badrequest.AlbumNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.UserNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;

import java.util.List;

public interface AlbumService {

    AlbumDto findOwnerAlbum(Long id) throws AlbumNotFoundException, ObtainingDataException;

    List<AlbumDto> findAllOwnerAlbums() throws UserNotFoundException;

    void createAlbum(CreateUpdateAlbumRequest albumDto) throws UserNotFoundException;

    void updateAlbum(Long id, CreateUpdateAlbumRequest albumDto) throws AlbumNotFoundException, UserNotFoundException,
            ObtainingDataException;

    void deleteAlbum(Long id) throws AlbumNotFoundException, ObtainingDataException;

    void uploadImage(Long id, byte[] image) throws AlbumNotFoundException, ObtainingDataException;
}
