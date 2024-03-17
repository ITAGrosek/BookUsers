package com.feri.bookusers;

import com.feri.bookusers.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDate;

public class UserServiceTest {

    @Test
    public void testUserSettersAndGetters() {
        String expectedName = "Janez";
        String expectedSurname = "Novak";
        int expectedAge = 30;
        String expectedEmail = "janez.novak@example.com";
        String expectedUsername = "jnovak";
        LocalDate expectedRegisteredAt = LocalDate.now();

        User user = new User();
        user.setName(expectedName);
        user.setSurname(expectedSurname);
        user.setAge(expectedAge);
        user.setEmail(expectedEmail);
        user.setUsername(expectedUsername);
        user.setRegisteredAt(expectedRegisteredAt);

        Assertions.assertEquals(expectedName, user.getName(), "The name should match");
        Assertions.assertEquals(expectedSurname, user.getSurname(), "The surname should match");
        Assertions.assertEquals(expectedAge, user.getAge(), "The age should match");
        Assertions.assertEquals(expectedEmail, user.getEmail(), "The email should match");
        Assertions.assertEquals(expectedUsername, user.getUsername(), "The username should match");
        Assertions.assertEquals(expectedRegisteredAt, user.getRegisteredAt(), "The registration date should match");
    }
}
