package ximagination80.repository;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Item extends AbstractPersistable<Long> {

    private String name;

    @OneToOne
    private Friend friend;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }
}
