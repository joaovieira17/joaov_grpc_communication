package com.votespesta.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    @Test
    void ensureUserNameIsSet() {
        final User user = new User("Albert","123");
        assertEquals("Albert", user.getUsername());
    }

    @Test
    void ensurePasswordIsSet() {
        final User user = new User("Albert","123");
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
    void ensureEmailIsUnSet() {
        final User user = new User("Albert","123");
        user.setEmail("albert@gmail.com");
        assertEquals("albert@gmail.com", user.getEmail());
        user.setEmail(null);
        assertThrows(NullPointerException.class, () ->user.getEmail().isEmpty());
    }

    @Test
    void ensureAddressIsUnSet() {
        final User user = new User("Albert","123");
        user.setAddress("Avenida dos Platanos");
        assertEquals("Avenida dos Platanos", user.getAddress());
        user.setAddress(null);
        assertThrows(NullPointerException.class, () -> user.getAddress().isEmpty());
    }


}
