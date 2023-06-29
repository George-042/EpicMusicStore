package by.georgprog.epicmusicstore.service.album;

import by.georgprog.epicmusicstore.config.security.UserDetailsImpl;
import by.georgprog.epicmusicstore.dto.AlbumDto;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import by.georgprog.epicmusicstore.exeption.unauthorized.AlbumNotFoundException;
import by.georgprog.epicmusicstore.exeption.unauthorized.UserNotFoundException;
import by.georgprog.epicmusicstore.mapper.AlbumMapper;
import by.georgprog.epicmusicstore.model.AlbumEntity;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import by.georgprog.epicmusicstore.repo.AlbumRepository;
import by.georgprog.epicmusicstore.repo.UserRepository;
import by.georgprog.epicmusicstore.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    @Override
    public AlbumDto findOwnerAlbum(Long id) throws AlbumNotFoundException, ObtainingDataException {
        Optional<AlbumEntity> albumEntity = albumRepository.findById(id);
        if (albumEntity.isEmpty()) {
            throw new AlbumNotFoundException();
        }
        UserDetailsImpl principal = SecurityContextUtils.getUserFromSecurityContext();
        if (!principal.getId().equals(albumEntity.get().getOwner().getId())) {
            throw new ObtainingDataException("You can not access the album");
        }
        return albumMapper.toDto(albumEntity.get());
    }

    @Override
    public List<AlbumDto> findAllOwnerAlbums() throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(SecurityContextUtils.getUserFromSecurityContext().getId())
                .orElseThrow(UserNotFoundException::new);
        return albumRepository.findAllByOwner(userEntity).stream().map(albumMapper::toDto).toList();
    }

    @Override
    public void createAlbum(AlbumDto albumDto) throws UserNotFoundException {
        AlbumEntity entity = albumMapper.toEntity(albumDto);
        entity.setOwner(userRepository.findById(SecurityContextUtils.getUserFromSecurityContext().getId())
                .orElseThrow(UserNotFoundException::new));
        albumRepository.save(entity);
    }

    @Override
    public void updateAlbum(Long id, AlbumDto albumDto) throws AlbumNotFoundException {
        if (albumRepository.findById(id).isEmpty()) {
            throw new AlbumNotFoundException();
        }
        albumDto.setId(id);
        albumRepository.save(albumMapper.toEntity(albumDto));
    }

    @Override
    public void deleteAlbum(Long id) throws AlbumNotFoundException {
        if (albumRepository.findById(id).isEmpty()) {
            throw new AlbumNotFoundException();
        }
        albumRepository.deleteById(id);
    }

    @Override
    public void uploadImage(Long id, byte[] image) throws AlbumNotFoundException {
        Optional<AlbumEntity> albumEntityOptional = albumRepository.findById(id);
        if (albumEntityOptional.isEmpty()) {
            throw new AlbumNotFoundException();
        }
        AlbumEntity albumEntity = albumEntityOptional.get();
        albumEntity.setAlbumPic(image);
        albumRepository.save(albumEntity);
    }
}
