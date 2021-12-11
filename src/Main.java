import domain.validation.FriendshipValidator;
import domain.validation.UserValidator;
import repository.database.FriendshipDbRepository;
import repository.database.MessageDbRepository;
import repository.database.UserDbRepository;
import repository.file.FriendshipFile;
import repository.file.UtilizatorFile;
import repository.memory.InMemoryRepository;
import service.FriendshipService;
import service.MainService;
import service.MessageService;
import service.UserService;
import ui.LoginUi;
import ui.UI;

public class Main {
    public static void main(String[] args) {
//        UtilizatorFile rep = new UtilizatorFile("src/useri.txt",new UserValidator());
//        FriendshipFile rep1 = new FriendshipFile("src/prietenii.txt",new FriendshipValidator());
//        MainService srv = new MainService(new UserService(rep),new FriendshipService(rep1));
//        UI ui = new UI(srv);
//        ui.start();

        FriendshipDbRepository r = new FriendshipDbRepository("jdbc:postgresql://localhost:5432/social_network", "postgres", "897891ioutz", new FriendshipValidator());
        UserDbRepository u = new UserDbRepository("jdbc:postgresql://localhost:5432/social_network", "postgres", "897891ioutz", new UserValidator());
        MessageDbRepository m = new MessageDbRepository("jdbc:postgresql://localhost:5432/social_network", "postgres", "897891ioutz");

        MainService s = new MainService(new UserService(u), new FriendshipService(r), new MessageService(m));
        LoginUi ui = new LoginUi(s);
        ui.start();
    }
}
