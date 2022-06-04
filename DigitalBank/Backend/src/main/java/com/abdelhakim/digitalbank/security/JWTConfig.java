package com.abdelhakim.digitalbank.security;

public class JWTConfig {
    public static final String SECRET = "blablasecurity";
    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN = 10 * 60 * 1000;
    public static final long EXPIRE_REFRESH_TOKEN = 200 * 60 * 1000;
}
