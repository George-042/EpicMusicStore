package by.georgprog.epicmusicstore.service.album;

import by.georgprog.epicmusicstore.dto.AlbumDto;
import by.georgprog.epicmusicstore.exeption.unauthorized.AlbumNotFoundException;
import by.georgprog.epicmusicstore.exeption.unauthorized.UserNotFoundException;
import by.georgprog.epicmusicstore.mapper.AlbumMapper;
import by.georgprog.epicmusicstore.model.AlbumEntity;
import by.georgprog.epicmusicstore.repo.AlbumRepository;
import by.georgprog.epicmusicstore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final UserService userService;
    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    @Override
    public List<AlbumEntity> findAll() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<AlbumEntity> findById(long id) {
        return albumRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public void save(AlbumEntity entity) {
        albumRepository.save(entity);
    }

    @Override
    public List<AlbumDto> findAllByOwner(Long musicianId) throws UserNotFoundException {
        return albumRepository.findAllByOwner(userService.findById(musicianId).orElseThrow(UserNotFoundException::new))
                .stream().map(albumMapper::toDto).toList();
    }

    @Override
    public Optional<AlbumDto> findByIdAndMusicianId(Long id, Long musicianId) throws UserNotFoundException {
        return albumRepository.findByIdAndOwner(id, userService.findById(musicianId)
                .orElseThrow(UserNotFoundException::new)).map(albumMapper::toDto);
    }

    @Override
    public List<AlbumDto> getUserAlbums(Long musicianId) throws UserNotFoundException {
        return findAllByOwner(musicianId);
    }

    @Override
    public AlbumDto getAlbum(Long musicianId, Long albumId) throws UserNotFoundException, AlbumNotFoundException {
        return findByIdAndMusicianId(albumId, musicianId).orElseThrow(AlbumNotFoundException::new);
    }

    @Override
    public void createAlbum(Long musicianId, AlbumDto albumDto) {
        AlbumEntity entity = albumMapper.toEntity(albumDto);
        if (userService.findById(musicianId).isPresent()) {
            entity.setOwner(userService.findById(musicianId).get());
            save(entity);
        }
    }

    @Override
    public void updateAlbum(Long musicianId, Long albumId, AlbumDto albumDto) throws UserNotFoundException,
            AlbumNotFoundException {
        AlbumEntity entity = findByIdAndMusicianId(albumId, musicianId).map(albumMapper::toEntity)
                .orElseThrow(AlbumNotFoundException::new);
        entity.setName(albumDto.getName());
        entity.setAlbumPic(albumDto.getAlbumImg());
        save(entity);
    }

    @Override
    public void deleteAlbum(Long musicianId, Long albumId) throws UserNotFoundException, AlbumNotFoundException {
        findByIdAndMusicianId(albumId, musicianId).orElseThrow(AlbumNotFoundException::new);
        deleteById(albumId);
    }

    @Override
    public void uploadImage(Long musicianId, Long albumId, byte[] image) throws AlbumNotFoundException {
        AlbumEntity entity = findById(albumId).orElseThrow(AlbumNotFoundException::new);
        entity.setAlbumPic(image);
        save(entity);
    }
}
