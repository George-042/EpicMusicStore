package by.georgprog.epicmusicstore.service.playlist;

import by.georgprog.epicmusicstore.dto.playlist.CreateUpdatePlaylistRequest;
import by.georgprog.epicmusicstore.dto.playlist.PlaylistDto;
import by.georgprog.epicmusicstore.exeption.badrequest.PlaylistNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.TrackNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.UserNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PlaylistService {

    PlaylistDto findPlaylist(Long id) throws PlaylistNotFoundException;

    List<PlaylistDto> findAllPlaylists();

    List<PlaylistDto> findAllOwnerPlaylists(Long id) throws UserNotFoundException;

    void createPlaylist(CreateUpdatePlaylistRequest playlistDto, MultipartFile img) throws UserNotFoundException, IOException, TrackNotFoundException;

    void updatePlaylist(Long id, CreateUpdatePlaylistRequest playlistDto, MultipartFile img) throws PlaylistNotFoundException, TrackNotFoundException, UserNotFoundException, IOException, ObtainingDataException;

    void deletePlaylist(Long id);
}
