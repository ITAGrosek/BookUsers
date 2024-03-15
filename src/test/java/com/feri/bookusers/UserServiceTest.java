package com.feri.bookusers;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.grpc.GrpcClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@QuarkusTest
public class UserServiceTest {

    @GrpcClient
    UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    @Test
    public void testCreateUserWithNegativeAge() {
        User.UserRequest request = User.UserRequest.newBuilder()
                .setName("Janez")
                .setSurname("Novak")
                .setAge(-33) // Negativna starost
                .setEmail("janez.novak@example.com")
                .setUsername("jnovak")
                .build();

        StatusRuntimeException thrown = assertThrows(StatusRuntimeException.class, () -> {
            userServiceBlockingStub.createUser(request);
        });

        // Preverite kodo statusa napake
        assertEquals(Status.INVALID_ARGUMENT.getCode(), thrown.getStatus().getCode());
    }
}
