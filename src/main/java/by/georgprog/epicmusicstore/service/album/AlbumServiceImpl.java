package by.georgprog.epicmusicstore.service.album;

import by.georgprog.epicmusicstore.config.security.UserDetailsImpl;
import by.georgprog.epicmusicstore.dto.AlbumDto;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import by.georgprog.epicmusicstore.exeption.unauthorized.AlbumNotFoundException;
import by.georgprog.epicmusicstore.exeption.unauthorized.UserNotFoundException;
import by.georgprog.epicmusicstore.mapper.AlbumMapper;
import by.georgprog.epicmusicstore.model.AlbumEntity;
import by.georgprog.epicmusicstore.repo.AlbumRepository;
import by.georgprog.epicmusicstore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public Optional<AlbumEntity> findById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public void save(AlbumEntity entity) {
        albumRepository.save(entity);
    }

    @Override
    public AlbumDto findOwnerAlbum(Long id) throws AlbumNotFoundException, ObtainingDataException {
        if (findById(id).isEmpty()) {
            throw new AlbumNotFoundException();
        }
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if (!principal.getId().equals(findById(id).get().getOwner().getId())) {
            throw new ObtainingDataException("You can not access the album");
        }
        return albumMapper.toDto(findById(id).get());
    }

    @Override
    public List<AlbumDto> findAllOwnerAlbums() throws UserNotFoundException {
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return albumRepository.findAllByOwner(userService.findById(principal.getId())
                .orElseThrow(UserNotFoundException::new)).stream().map(albumMapper::toDto).toList();
    }

    @Override
    public void createAlbum(AlbumDto albumDto) throws UserNotFoundException {
        AlbumEntity entity = albumMapper.toEntity(albumDto);
        entity.setOwner(userService.findById(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getId()).orElseThrow(UserNotFoundException::new));
        save(entity);
    }

    @Override
    public void updateAlbum(Long id, AlbumDto albumDto) throws AlbumNotFoundException {
        if (findById(id).isEmpty()) {
            throw new AlbumNotFoundException();
        }
        save(albumMapper.toEntity(albumDto));
    }

    @Override
    public void deleteAlbum(Long id) throws AlbumNotFoundException {
        if (findById(id).isEmpty()) {
            throw new AlbumNotFoundException();
        }
        deleteById(id);
    }

    @Override
    public void uploadImage(Long id, byte[] image) throws AlbumNotFoundException {
        if (findById(id).isEmpty()) {
            throw new AlbumNotFoundException();
        }
        AlbumEntity albumEntity = findById(id).get();
        albumEntity.setAlbumPic(image);
        save(albumEntity);
    }
}
