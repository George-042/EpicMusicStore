package by.georgprog.epicmusicstore.service.jwt;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JwtProperties {

    private static final long EXPIRATION_TIME = TimeUnit.DAYS.toMillis(1);
    private static final String SECRET_KEY = "3F4528482B4D6250655368566D597133743677397A24432646294A404E635266";
    private static final Map<String, Object> HEADER_PARAMETERS = Map.of("typ", "JWT", "alg", "HS256");

    public static Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + EXPIRATION_TIME);
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }

    public static Map<String, Object> getHeaderParameters() {
        return HEADER_PARAMETERS;
    }
}
