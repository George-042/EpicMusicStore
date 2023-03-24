package by.georgprog.epicmusicstore.dto;

import by.georgprog.epicmusicstore.model.AlbumEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto {

    private Long id;
    private String name;
    private UserDto owner;
    private Byte[] albumPic;

    public AlbumDto(AlbumEntity albumEntity) {
        BeanUtils.copyProperties(albumEntity, this);
    }
}
