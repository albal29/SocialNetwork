package domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Friendship extends Entity<Tuple<Long,Long>> {

    LocalDateTime date;

    public Friendship(Long id1,Long id2) {
        this.setId(new Tuple<Long,Long>(id1,id2));
        this.date = LocalDateTime.now();
    }

    public Friendship(Long id1,Long id2,LocalDateTime time) {
        this.setId(new Tuple<Long,Long>(id1,id2));
        this.date = time;
    }
    public Friendship(){

    }

    /**
     *
     * @return the date when the friendship was created
     */
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friendship that = (Friendship) o;
        return Objects.equals(this.getId(),that.getId());
    }


}
