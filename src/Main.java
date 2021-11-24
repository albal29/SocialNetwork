import domain.Friendship;
import domain.Message;
import domain.Tuple;
import domain.User;
import domain.validation.FriendshipValidator;
import domain.validation.UserValidator;
import domain.validation.ValidationException;
import repository.Repository;
import repository.database.FriendshipDbRepository;
import repository.database.MessageDbRepository;
import repository.database.UserDbRepository;
import repository.file.FriendshipFile;
import repository.file.UtilizatorFile;
import repository.memory.InMemoryRepository;
import service.FriendshipService;
import service.MainService;
import service.UserService;
import ui.UI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
//        UtilizatorFile rep = new UtilizatorFile("src/useri.txt",new UserValidator());
//        FriendshipFile rep1 = new FriendshipFile("src/prietenii.txt",new FriendshipValidator());
//        MainService srv = new MainService(new UserService(rep),new FriendshipService(rep1));
//        UI ui = new UI(srv);
//        ui.start();

//        FriendshipDbRepository r = new FriendshipDbRepository("jdbc:postgresql://localhost:5432/social_network","postgres","897891ioutz",new FriendshipValidator());
       UserDbRepository u = new UserDbRepository("jdbc:postgresql://localhost:5432/social_network","postgres","897891ioutz",new UserValidator());
//        MainService s = new MainService(new UserService(u),new FriendshipService(r));
//        UI ui = new UI(s);
//        ui.start();
        List<User> list = new ArrayList<>();
        list.add(u.findOne(2L));
        list.add(u.findOne(3L));
        Message m = new Message(u.findOne(1L),list,"exemplu",null);
        MessageDbRepository r = new MessageDbRepository("jdbc:postgresql://localhost:5432/social_network","postgres","897891ioutz");
        r.save(m);
    }}
