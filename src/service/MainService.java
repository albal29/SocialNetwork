package service;

import Networking.Networking;
import domain.DTO;
import domain.Friendship;
import domain.Tuple;
import domain.User;
import domain.validation.ValidationException;
import repository.RepoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainService {
    UserService us;
    FriendshipService fs;

    public MainService(UserService us, FriendshipService fs) {
        this.us = us;
        this.fs = fs;
    }

    public User addUser(User entity){
        return us.save(entity);
    }

    public User findUser(Long id){
        return us.findOne(id);
    }

    public User deleteUser(Long id){
        User u = us.delete(id);

       for(Friendship f :findAllFriendships()){
           if(f.getId().getRight()==u.getId())
               removeFriendship(new Tuple<>(f.getId().getLeft(),u.getId()));
            if(f.getId().getLeft()==u.getId())
            removeFriendship(new Tuple<>(u.getId(),f.getId().getRight()));
    }


        return u;
    }

    public Iterable<User> findAllUsers(){
        return us.findAll();
    }

    public Iterable<Friendship> findAllFriendships(){
        return fs.findAll();
    }

    public Friendship addFriendship(Friendship f){
        if(us.findOne(f.getId().getLeft())==null||us.findOne(f.getId().getRight())==null) {
            throw new RepoException("Friendship must be between existent users!");
        }
        fs.save(f);
        return null;
    }

    public Friendship removeFriendship(Tuple<Long,Long> id){
        Friendship f = fs.findOne(id);
        fs.delete(id);
        us.findOne(f.getId().getLeft()).remFriend(us.findOne(f.getId().getRight()));
        us.findOne(f.getId().getRight()).remFriend(us.findOne(f.getId().getLeft()));
        return f;
    }

    public Friendship findFrienship(Tuple<Long,Long> id){
        return fs.findOne(id);
    }

    public int noOfCommunities(){
        List<Long> aux = us.getListOfUID();
        Networking net = new Networking(fs.findAll(),aux, aux.get(aux.size()-1)+1);
        net.setFriendMatrix();
        return net.numberOfCommunities();

    }
    public List<Long> mostSociableCommunity(){
        List<Long> aux = us.getListOfUID();
        Networking net = new Networking(fs.findAll(),aux, aux.get(aux.size()-1)+1);
        net.setFriendMatrix();
        return net.mostSociableCommunity();

    }

    public List<DTO> getUserFriends(Long id){
        List<Friendship> friendships = new ArrayList<Friendship>();
        findAllFriendships().forEach(friendships::add);
        List<DTO> friends = friendships.stream()
                .filter(f -> f.getId().getLeft() == id)
                .map(f -> new DTO(findUser(f.getId().getLeft()).getFirstName(),findUser(f.getId().getLeft()).getLastName(),f.getDate()))
                .collect(Collectors.toList());
       friends.addAll(friendships.stream()
               .filter(f -> f.getId().getRight() == id)
               .map(f -> new DTO(findUser(f.getId().getRight()).getFirstName(),findUser(f.getId().getRight()).getLastName(),f.getDate()))
               .collect(Collectors.toList()));
       return friends;
    }

    public User updateUser(Long id,String fName,String lName,String password){
        User u = findUser(id);
        if(u==null) throw new RepoException("User doesn't exist!");
        u.setFirstName(fName);
        u.setLastName(lName);
        u.setPassword(password);
        return us.update(u);
    }
}
