package com.feri.bookusers;

import com.feri.bookusers.model.User;
import com.feri.bookusers.repository.UserRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.time.LocalDate;

@QuarkusTest
public class UserServiceTest {

    @InjectMock
    UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        // Nastavitev testnega uporabnika
        testUser = new User("Janez", "Novak", 30, "janez@example.com", "jnovak123");
        testUser.setId("123");
        testUser.setRegisteredAt(LocalDate.now());

        // Mock vrnitev ko bo poklicana metoda findUserById
        Mockito.when(userRepository.findUserById("123")).thenReturn(testUser);
    }

    @Test
    public void testCreateAndFindUser() {
        // Pričakovanje: ko kličemo `findUserById` z določenim ID, naj vrne `expectedUser`
        User foundUser = userRepository.findUserById("123");

        // Preverjanje
        Assertions.assertNotNull(foundUser, "The user should not be null");
        Assertions.assertEquals(testUser.getName(), foundUser.getName(), "The names should match");
        Assertions.assertEquals(testUser.getSurname(), foundUser.getSurname(), "The surnames should match");
        Assertions.assertEquals(testUser.getAge(), foundUser.getAge(), "The ages should match");
        Assertions.assertEquals(testUser.getEmail(), foundUser.getEmail(), "The emails should match");
        Assertions.assertEquals(testUser.getUsername(), foundUser.getUsername(), "The usernames should match");
        Assertions.assertEquals(testUser.getRegisteredAt(), foundUser.getRegisteredAt(), "The registered dates should match");
    }
}
