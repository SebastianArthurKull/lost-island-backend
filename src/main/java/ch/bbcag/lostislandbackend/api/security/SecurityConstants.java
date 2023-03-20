package ch.bbcag.lostislandbackend.api.security;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTH_RESPONSE_ATTRIBUTE_NAME = "token";
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String ROLE_PREFIX = "ROLE_";

    public static final String[] API_DOCUMENTATION_URLS = {
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**"
    };
}
