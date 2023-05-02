package by.georgprog.epicmusicstore.service.album;

import by.georgprog.epicmusicstore.dto.AlbumDto;
import by.georgprog.epicmusicstore.exeption.unauthorized.AlbumNotFoundException;
import by.georgprog.epicmusicstore.exeption.unauthorized.UserNotFoundException;
import by.georgprog.epicmusicstore.model.AlbumEntity;
import by.georgprog.epicmusicstore.service.DaoService;

import java.util.List;
import java.util.Optional;

public interface AlbumService extends DaoService<AlbumEntity> {

    List<AlbumDto> findAllByOwner(Long musicianId) throws UserNotFoundException;

    Optional<AlbumDto> findByIdAndMusicianId(Long id, Long musicianId) throws UserNotFoundException;

    List<AlbumDto> getUserAlbums(Long musicianId) throws UserNotFoundException;

    AlbumDto getAlbum(Long musicianId, Long albumId) throws UserNotFoundException, AlbumNotFoundException;

    void createAlbum(Long musicianId, AlbumDto albumDto);

    void updateAlbum(Long musicianId, Long albumId, AlbumDto albumDto) throws AlbumNotFoundException, UserNotFoundException;

    void deleteAlbum(Long musicianId, Long albumId) throws AlbumNotFoundException, UserNotFoundException;

    void uploadImage(Long musicianId, Long albumId, byte[] image) throws AlbumNotFoundException;
}
