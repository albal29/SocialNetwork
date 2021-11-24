package repository.database;

import domain.Friendship;
import domain.Message;
import domain.validation.Validator;
import repository.Repository;

import java.sql.*;

public class MessageDbRepository implements Repository<Integer, Message> {
    String url;
    String username;
    String password;
    public MessageDbRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Message findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Message> findAll() {
        return null;
    }

    public Integer size(){
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) from message");

             ResultSet resultSet = statement.executeQuery()) {

                 return resultSet.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    @Override
    public Message save(Message entity) {
        return null;
    }

    @Override
    public Message delete(Integer integer) {
        return null;
    }

    @Override
    public Message update(Message entity) {
        return null;
    }
}
