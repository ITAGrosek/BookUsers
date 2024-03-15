package com.feri.bookusers;

import com.feri.bookusers.model.User;
import com.feri.bookusers.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@io.quarkus.grpc.GrpcService
public class UserService extends com.feri.bookusers.UserServiceGrpc.UserServiceImplBase {

    @Inject
    UserRepository userRepository;

    @Override
    public void createUser(com.feri.bookusers.User.UserRequest request, StreamObserver<com.feri.bookusers.User.UserResponse> responseObserver) {
        User user = new User(request.getName(), request.getSurname(), request.getAge(), request.getEmail(), request.getUsername());
        userRepository.createUser(user);
        com.feri.bookusers.User.UserResponse response = userToUserResponse(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(com.feri.bookusers.User.UserRequest request, StreamObserver<com.feri.bookusers.User.UserResponse> responseObserver) {
        User user = userRepository.findUserById(request.getId());
        if (user != null) {
            com.feri.bookusers.User.UserResponse response = userToUserResponse(user);
            responseObserver.onNext(response);
        } else {
            responseObserver.onError(new Throwable("User not found with ID: " + request.getId()));
        }
        responseObserver.onCompleted();
    }

    @Override
    public void updateUser(com.feri.bookusers.User.UserRequest request, StreamObserver<com.feri.bookusers.User.UserResponse> responseObserver) {
        User user = userRepository.updateUser(request.getId(), request.getName(), request.getSurname(), request.getAge(), request.getEmail(), request.getUsername());
        com.feri.bookusers.User.UserResponse response = userToUserResponse(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUser(com.feri.bookusers.User.UserRequest request, StreamObserver<com.feri.bookusers.User.UserResponse> responseObserver) {
        userRepository.deleteUser(request.getId());
        // Brez pošiljanja praznega odgovora, ker je operacija brisanja zaključena in ni potrebno vrniti podatkov
        responseObserver.onCompleted();
    }


    @Override
    public void listUsers(com.feri.bookusers.User.UserRequest request, StreamObserver<com.feri.bookusers.User.UserResponse> responseObserver) {
        List<User> users = userRepository.findAllUsers();
        List<com.feri.bookusers.User.UserResponse> responses = users.stream().map(this::userToUserResponse).collect(Collectors.toList());
        responses.forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    private com.feri.bookusers.User.UserResponse userToUserResponse(User user) {
        return com.feri.bookusers.User.UserResponse.newBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setSurname(user.getSurname())
                .setAge(user.getAge())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername())
                .setRegisteredAt(user.getRegisteredAt().toString())
                .build();
    }
}
