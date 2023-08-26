package by.georgprog.epicmusicstore.dto.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateAlbumRequest {

    private String name;
    private MultipartFile albumImg;
}
