package com.feri.bookusers.repository;

import com.feri.bookusers.model.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserRepository {

    @Inject
    MongoClient mongoClient;

    private MongoCollection<Document> getCollection() {
        MongoDatabase database = mongoClient.getDatabase("usersdb");
        return database.getCollection("users");
    }

    public void createUser(User user) {
        Document doc = new Document()
                .append("name", user.getName())
                .append("surname", user.getSurname())
                .append("age", user.getAge())
                .append("email", user.getEmail())
                .append("username", user.getUsername())
                .append("registeredAt", user.getRegisteredAt().toString());
        getCollection().insertOne(doc);
        // After insertion, set the generated ID to the user object
        user.setId(doc.getObjectId("_id").toString());
    }

    public User findUserById(String id) {
        Document doc = getCollection().find(Filters.eq("_id", new ObjectId(id))).first();
        return documentToUser(doc);
    }

    public User updateUser(String id, String name, String surname, int age, String email, String username) {
        getCollection().updateOne(Filters.eq("_id", new ObjectId(id)),
                Updates.combine(
                        Updates.set("name", name),
                        Updates.set("surname", surname),
                        Updates.set("age", age),
                        Updates.set("email", email),
                        Updates.set("username", username)
                ));
        return findUserById(id);
    }

    public void deleteUser(String id) {
        getCollection().deleteOne(Filters.eq("_id", new ObjectId(id)));
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        for (Document doc : getCollection().find()) {
            users.add(documentToUser(doc));
        }
        return users;
    }

    private User documentToUser(Document doc) {
        if (doc == null) {
            return null;
        }
        User user = new User();
        user.setId(doc.getObjectId("_id").toString());
        user.setName(doc.getString("name"));
        user.setSurname(doc.getString("surname"));
        user.setAge(doc.getInteger("age"));
        user.setEmail(doc.getString("email"));
        user.setUsername(doc.getString("username"));
        user.setRegisteredAt(LocalDate.parse(doc.getString("registeredAt"))); // Make sure to convert this properly from String to LocalDate
        return user;
    }
}
