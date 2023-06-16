package com.authorization.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    void ensureUserNameIsAccepted() {
        final User user = new User("Albert","123");
        assertEquals("Albert", user.getUsername());
        User user1 = new User();
        user1.setUsername("Albert");
        assertEquals("Albert", user1.getUsername());
    }

    @Test
    void ensurePasswordIsAccepted() {
        final User user = new User("Albert","123");
        assertEquals("123", user.getPassword());
        User user1 = new User();
        user1.setPassword("123");
        assertEquals("123", user.getPassword());
    }

    @Test
    void ensureEmailIsSet() {
        final User user = new User("Albert","123");
        user.setEmail("albert@gmail.com");
        assertEquals("albert@gmail.com", user.getEmail());
    }

    @Test
    void ensureAddressIsSet() {
        final User user = new User("Albert","123");
        user.setAddress("Avenida dos Platanos");
        assertEquals("Avenida dos Platanos", user.getAddress());
    }

    @Test
    void ensureEverythingIsAcceptedAndInPlace() {
        final User user = new User("Albert","123", "albert@gmail.com","Avenida dos Platanos");
        assertEquals("Albert", user.getUsername());
        assertEquals("123", user.getPassword());
        assertEquals("albert@gmail.com", user.getEmail());
        assertEquals("Avenida dos Platanos", user.getAddress());
    }

    @Test
    void ensureRoleIsGiven() {
        final User user = new User("Albert","123", "albert@gmail.com","Avenida dos Platanos");
        user.addAuthority(new Role(Role.MODERATOR));
        List<Role> roles= new ArrayList<>(user.getRoles());

        assertEquals(new Role(Role.MODERATOR), roles.get(0));
    }
}
