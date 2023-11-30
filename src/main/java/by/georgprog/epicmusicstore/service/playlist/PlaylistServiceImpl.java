package by.georgprog.epicmusicstore.service.playlist;

import by.georgprog.epicmusicstore.dto.playlist.CreateUpdatePlaylistRequest;
import by.georgprog.epicmusicstore.dto.playlist.PlaylistDto;
import by.georgprog.epicmusicstore.exeption.badrequest.PlaylistNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.TrackNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.UserNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import by.georgprog.epicmusicstore.mapper.PlaylistMapper;
import by.georgprog.epicmusicstore.model.PlaylistEntity;
import by.georgprog.epicmusicstore.model.TrackEntity;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import by.georgprog.epicmusicstore.repo.PlaylistRepository;
import by.georgprog.epicmusicstore.repo.TrackRepository;
import by.georgprog.epicmusicstore.repo.UserRepository;
import by.georgprog.epicmusicstore.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final TrackRepository trackRepository;
    private final PlaylistMapper playlistMapper;

    @Override
    public PlaylistDto findPlaylist(Long id) throws PlaylistNotFoundException {
        return playlistMapper.toDto(playlistRepository.findById(id).orElseThrow(PlaylistNotFoundException::new));
    }

    @Override
    public List<PlaylistDto> findAllPlaylists() {
        return playlistRepository.findAll().stream().map(playlistMapper::toDto).toList();
    }

    @Override
    public List<PlaylistDto> findAllOwnerPlaylists(Long id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return playlistRepository.findAllByOwner(userEntity).stream().map(playlistMapper::toDto).toList();
    }

    @Override
    public void createPlaylist(CreateUpdatePlaylistRequest playlistDto, MultipartFile img) throws UserNotFoundException,
            TrackNotFoundException, IOException {
        PlaylistEntity playlistEntity = fillInPlaylist(playlistDto, img);
        playlistRepository.save(playlistEntity);
    }

    @Override
    public void updatePlaylist(Long id, CreateUpdatePlaylistRequest playlistDto, MultipartFile img) throws
            PlaylistNotFoundException, TrackNotFoundException, UserNotFoundException, IOException,
            ObtainingDataException {
        PlaylistEntity playlistEntityFromDb = playlistRepository.findById(id)
                .orElseThrow(PlaylistNotFoundException::new);
        if (!playlistEntityFromDb.getOwner().getId()
                .equals(SecurityContextUtils.getUserFromSecurityContext().getId())) {
            throw new ObtainingDataException("You can not access the playlist");
        }
        PlaylistEntity newPlaylistEntity = fillInPlaylist(playlistDto, img);
        newPlaylistEntity.setId(id);
        playlistRepository.save(newPlaylistEntity);
    }

    @Override
    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    private PlaylistEntity fillInPlaylist(CreateUpdatePlaylistRequest playlistDto, MultipartFile img)
            throws TrackNotFoundException, UserNotFoundException, IOException {
        PlaylistEntity playlistEntity = new PlaylistEntity();

        List<TrackEntity> tracks = new ArrayList<>();
        for (Long trackId : playlistDto.getTracks()) {
            tracks.add(trackRepository.findById(trackId).orElseThrow(TrackNotFoundException::new));
        }

        playlistEntity.setName(playlistDto.getName());
        playlistEntity.setTracks(tracks);
        playlistEntity.setOwner(userRepository.findById(SecurityContextUtils.getUserFromSecurityContext().getId())
                .orElseThrow(UserNotFoundException::new));
        if (img != null) {
            playlistEntity.setPlaylistPic(img.getBytes());
        }
        return playlistEntity;
    }
}
