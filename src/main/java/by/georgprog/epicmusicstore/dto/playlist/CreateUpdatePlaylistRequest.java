package by.georgprog.epicmusicstore.dto.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdatePlaylistRequest {
    private String name;
    private List<Long> tracks;
}
