package by.georgprog.epicmusicstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestDto {

    private String principal;
    private String credentials;
}
