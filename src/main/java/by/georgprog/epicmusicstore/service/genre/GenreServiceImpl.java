package by.georgprog.epicmusicstore.service.genre;

import by.georgprog.epicmusicstore.dto.genre.CreateUpdateGenreRequest;
import by.georgprog.epicmusicstore.dto.genre.GenreDto;
import by.georgprog.epicmusicstore.exeption.badrequest.GenreNotFoundException;
import by.georgprog.epicmusicstore.exeption.forbidden.AccessDeniedException;
import by.georgprog.epicmusicstore.mapper.GenreMapper;
import by.georgprog.epicmusicstore.model.GenreEntity;
import by.georgprog.epicmusicstore.model.user.UserRole;
import by.georgprog.epicmusicstore.repo.GenreRepository;
import by.georgprog.epicmusicstore.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreMapper genreMapper;
    private final GenreRepository genreRepository;

    @Override
    public GenreDto findGenre(Long id) throws GenreNotFoundException {
        return genreRepository.findById(id).map(genreMapper::toDto).orElseThrow(GenreNotFoundException::new);
    }

    @Override
    public List<GenreDto> findAllGenres() {
        return genreRepository.findAll().stream().map(genreMapper::toDto).toList();
    }

    @Override
    public void createGenre(CreateUpdateGenreRequest genreDto) throws AccessDeniedException {
        if (SecurityContextUtils.getUserFromSecurityContext().getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals(UserRole.ADMIN.name()))) {
            throw new AccessDeniedException();
        }
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setName(genreDto.getName());
        genreRepository.save(genreEntity);
    }

    @Override
    public void updateGenre(Long id, CreateUpdateGenreRequest genreDto) throws AccessDeniedException,
            GenreNotFoundException {
        if (SecurityContextUtils.getUserFromSecurityContext().getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals(UserRole.ADMIN.name()))) {
            throw new AccessDeniedException();
        }
        GenreEntity genreEntity = genreRepository.findById(id).orElseThrow(GenreNotFoundException::new);
        genreEntity.setName(genreDto.getName());
        genreRepository.save(genreEntity);
    }

    @Override
    public void deleteGenre(Long id) throws AccessDeniedException, GenreNotFoundException {
        if (SecurityContextUtils.getUserFromSecurityContext().getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals(UserRole.ADMIN.name()))) {
            throw new AccessDeniedException();
        }
        genreRepository.findById(id).orElseThrow(GenreNotFoundException::new);
        genreRepository.deleteById(id);
    }
}
