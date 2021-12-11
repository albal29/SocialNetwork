package repository.file;

import domain.Friendship;
import domain.Tuple;
import domain.validation.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class FriendshipFile extends AbstractFileRepository<Tuple<Long, Long>, Friendship> {
    public FriendshipFile(String fileName, Validator<Friendship> validator) {
        super(fileName, validator);
    }

    @Override
    protected Friendship extractEntity(List<String> attributes) {
        return new Friendship(Long.parseLong(attributes.get(0)), Long.parseLong(attributes.get(1)), LocalDateTime.parse(attributes.get(2)), attributes.get(3));
    }

    @Override
    protected String createEntityAsString(Friendship entity) {
        return entity.getId().getLeft() + ";" + entity.getId().getRight() + ";" + entity.getDate() + ";" + entity.getStatut();
    }
}
