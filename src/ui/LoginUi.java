package ui;

import domain.Friendship;
import domain.UserAccount;
import repository.RepoException;
import service.MainService;


public class LoginUi extends UI{
    UserAccount userAccount;

    public LoginUi(MainService srv) {
        super(srv);
    }

    public void firstMenu() {
        System.out.println("1.Log in\n2.Sign up\n3.exit\n");
    }

    public void secondMenu(){
        System.out.println("1.Add friend\n2.See requests\n3.See friends\n4.Sign off\n");
    }

    public boolean logInToAccount() {
        System.out.println("Username");
        String userName = scan.next();
        System.out.println("Password");
        String password = scan.next();

        userAccount = new UserAccount(userName, password);
        for (var user : srv.findAllUsers()
        ) {
            if (user.getUserName().equals(userAccount.getUserName()) && user.getPassword().equals(userAccount.getPassword())) {
                userAccount.setId(user.getId());
                return true;
            }
            if (user.getUserName().equals(userAccount.getUserName()) && !user.getPassword().equals(userAccount.getPassword())) {
                System.out.println("Incorrect password");
                return false;
            }
        }
        return false;
    }

    public void friendshipsMng() {
        if(srv.findPendingFriendships().spliterator().getExactSizeIfKnown()==0){
            System.out.println("There are no pending friendships.");
            return;
        }
        for (var x : srv.findPendingFriendships()) {
            if (x.getId().getRight().equals(userAccount.getId())) {
                System.out.println(srv.findUser(x.getId().getLeft()) + " wants to be friends with you.");
                System.out.println("Do you accept? yes/no");
                String answer = scan.next();
                switch (answer) {
                    case "yes" -> {
                        srv.updateFriendship(new Friendship(x.getId().getLeft(), x.getId().getRight(), x.getDate(), "Approved"));
                        break;
                    }
                    case "no" -> {
                        srv.updateFriendship(new Friendship(x.getId().getLeft(), x.getId().getRight(), x.getDate(), "Rejected"));
                        break;
                    }
                    default -> System.out.println("It will wait");
                }
            } else System.out.println("No requests");
        }
    }

    public void addFriend() {
        System.out.println("Give username:");
        String username = scan.next();
        for (var user : srv.findAllUsers()) {
            if (user.getUserName().equals(username)) {
                try{
                    srv.addFriendship(new Friendship(userAccount.getId(), user.getId()));
                }catch (RepoException re){
                    System.out.println(re.getMessage());
                }
            }
        }
    }

    public void checkRequests(){
        if(srv.findPendingFriendships().spliterator().getExactSizeIfKnown()>0){
            System.out.println("You have new friendship Requests");
        }
    }


    public void start() {
        boolean ok = true;
        int command;
        while (ok) {
            firstMenu();
            System.out.println("Option:");
            command = scan.nextInt();
            switch (command) {
                case 1 -> {
                    startLogIn();
                    break;
                }
                case 2 -> {
                    addUser();
                    break;
                }
                case 3 -> ok = false;
            }
        }
    }


    public void startLogIn(){
        System.out.println("Please login first");
        boolean ok = logInToAccount();
        int command;
        while (ok) {
            checkRequests();
            secondMenu();
            System.out.println("Option:");
            command = scan.nextInt();
            switch (command) {
                case 1 -> {
                    addFriend();
                    break;
                }
                case 2 -> {
                    friendshipsMng();
                    break;
                }
                case 3 -> {
                    userFriends(userAccount.getId());
                }
                case 4 -> ok = false;
            }
        }
    }
}
