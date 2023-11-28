package by.georgprog.epicmusicstore.service.album;

import by.georgprog.epicmusicstore.dto.album.AlbumDto;
import by.georgprog.epicmusicstore.dto.album.CreateUpdateAlbumRequest;
import by.georgprog.epicmusicstore.exeption.badrequest.AlbumNotFoundException;
import by.georgprog.epicmusicstore.exeption.badrequest.UserNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.ObtainingDataException;
import by.georgprog.epicmusicstore.mapper.AlbumMapper;
import by.georgprog.epicmusicstore.model.AlbumEntity;
import by.georgprog.epicmusicstore.model.user.UserEntity;
import by.georgprog.epicmusicstore.repo.AlbumRepository;
import by.georgprog.epicmusicstore.repo.UserRepository;
import by.georgprog.epicmusicstore.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    @Override
    public AlbumDto findOwnerAlbum(Long id) throws AlbumNotFoundException, ObtainingDataException {
        AlbumEntity albumEntity = albumRepository.findById(id).orElseThrow(AlbumNotFoundException::new);
        if (!SecurityContextUtils.getUserFromSecurityContext().getId().equals(albumEntity.getOwner().getId())) {
            throw new ObtainingDataException("You can not access the album");
        }
        return albumMapper.toDto(albumEntity);
    }

    @Override
    public List<AlbumDto> findAllOwnerAlbums() throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(SecurityContextUtils.getUserFromSecurityContext().getId())
                .orElseThrow(UserNotFoundException::new);
        return albumRepository.findAllByOwner(userEntity).stream().map(albumMapper::toDto).toList();
    }

    @Override
    public void createAlbum(CreateUpdateAlbumRequest albumDto, MultipartFile albumImg) throws UserNotFoundException,
            IOException {
        AlbumEntity entity = new AlbumEntity();
        entity.setName(albumDto.getName());
        if (albumImg != null) {
            entity.setAlbumPic(albumImg.getBytes());
        }
        entity.setOwner(userRepository.findById(SecurityContextUtils.getUserFromSecurityContext().getId())
                .orElseThrow(UserNotFoundException::new));
        albumRepository.save(entity);
    }

    @Override
    public void updateAlbum(Long id, CreateUpdateAlbumRequest albumDto, MultipartFile albumImg) throws AlbumNotFoundException,
            ObtainingDataException, IOException {
        AlbumEntity albumEntityFromDb = albumRepository.findById(id).orElseThrow(AlbumNotFoundException::new);
        if (!albumEntityFromDb.getOwner().getId().equals(SecurityContextUtils.getUserFromSecurityContext().getId())) {
            throw new ObtainingDataException("You can not access the album");
        }
        albumEntityFromDb.setName(albumDto.getName());
        albumEntityFromDb.setAlbumPic(albumImg.getBytes());
        albumRepository.save(albumEntityFromDb);
    }

    @Override
    public void deleteAlbum(Long id) throws AlbumNotFoundException, ObtainingDataException {
        AlbumEntity albumEntity = albumRepository.findById(id).orElseThrow(AlbumNotFoundException::new);
        if (!albumEntity.getOwner().getId().equals(SecurityContextUtils.getUserFromSecurityContext().getId())) {
            throw new ObtainingDataException("You can not access the album");
        }
        albumRepository.deleteById(id);
    }

    @Override
    public void uploadImage(Long id, MultipartFile albumImg) throws AlbumNotFoundException, ObtainingDataException,
            IOException {
        AlbumEntity albumEntity = albumRepository.findById(id).orElseThrow(AlbumNotFoundException::new);
        if (!albumEntity.getOwner().getId().equals(SecurityContextUtils.getUserFromSecurityContext().getId())) {
            throw new ObtainingDataException("You can not access the album");
        }
        albumEntity.setAlbumPic(albumImg.getBytes());
        albumRepository.save(albumEntity);
    }
}
