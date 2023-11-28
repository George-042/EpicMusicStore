package by.georgprog.epicmusicstore.dto.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUpdateGenreRequest {

    private String name;
}
