package service;

import domain.User;
import repository.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserService implements Service<Long, User>{
    private Repository<Long,User> rep;

    public UserService(Repository<Long, User> rep) {
        this.rep = rep;
    }

    @Override
    public User findOne(Long aLong) {
        return rep.findOne(aLong);
    }

    @Override
    public Iterable<User> findAll() {
        return rep.findAll();
    }

    @Override
    public User save(User entity) {
        return rep.save(entity);
    }

    @Override
    public User delete(Long aLong) {
        return rep.delete(aLong);
    }

    @Override
    public User update(User entity) {
        return rep.update(entity);
    }

    public List<Long> getListOfUID(){
        ArrayList<Long> aux = new ArrayList<>();
        for(User u:findAll()){
            aux.add(u.getId());
        }
        Collections.sort(aux);
        return aux;
    }

}
