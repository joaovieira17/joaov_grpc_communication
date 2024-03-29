package com.authorization.model;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;


@Value
@AllArgsConstructor
public class Role implements GrantedAuthority{

    public static final String REG_USER = "REG_USER";
    public static final String MODERATOR = "MODERATOR";
    public static final String EMPLOYEE = "EMPLOYEE";
    public static final String ADMIN = "ADMIN";


    private String authority;
}

