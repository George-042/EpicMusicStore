package by.georgprog.epicmusicstore.dto.track;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateTrackRequest {

    private String name;
    private Long albumId;
    private List<Long> authorsId;
    private List<Long> genresId;
}
