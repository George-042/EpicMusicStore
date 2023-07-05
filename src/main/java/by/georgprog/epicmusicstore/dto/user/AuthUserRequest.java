package by.georgprog.epicmusicstore.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserRequest {

    private String principal;
    private String credentials;
}
