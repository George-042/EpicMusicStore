package by.georgprog.epicmusicstore.service.album;

import by.georgprog.epicmusicstore.dto.album.AlbumDto;
import by.georgprog.epicmusicstore.dto.album.CreateUpdateAlbumRequest;
import by.georgprog.epicmusicstore.exeption.badrequest.AlbumNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.UserNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AlbumService {

    AlbumDto findOwnerAlbum(Long id) throws AlbumNotFoundException, ObtainingDataException;

    List<AlbumDto> findAllOwnerAlbums() throws UserNotFoundException;

    void createAlbum(CreateUpdateAlbumRequest albumDto, MultipartFile albumImg) throws UserNotFoundException, IOException;

    void updateAlbum(Long id, CreateUpdateAlbumRequest albumDto, MultipartFile albumImg) throws AlbumNotFoundException, UserNotFoundException,
            ObtainingDataException, IOException;

    void deleteAlbum(Long id) throws AlbumNotFoundException, ObtainingDataException;

    void uploadImage(Long id, MultipartFile albumImg) throws AlbumNotFoundException, ObtainingDataException, IOException;
}
