package repository.database;

import domain.Friendship;
import domain.Message;
import domain.User;
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
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) as nr from messages");

             ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return Integer.valueOf(resultSet.getInt("nr"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public void saveChat(Message entity){
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("insert into chat(mid,uid) values(?,?)"))
        {
            for(User u: entity.getTo()){
                statement.setInt(1,size());
                statement.setInt(2,u.getId().intValue());
                statement.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Message save(Message entity) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("insert into messages values(?,?,?,?,?) "))
             {
              statement.setInt(1,size()+1);
              statement.setString(2,entity.getMessage());
              statement.setString(3,entity.getData().toString());
              statement.setInt(4,entity.getFrom().getId().intValue());
              if(entity.getReply()==null)
                    statement.setInt(5,-1);
              else
                  statement.setInt(5,entity.getReply().getId());

              statement.executeUpdate();
              saveChat(entity);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
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
