package jsonprocessing.goldeneagle.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @Size(min = 3, message = "Minimum 3 symbols required for last name!")
    private String lastName;

    @Column()
    private int age;

    @OneToMany(mappedBy = "seller")
    private List<Product> soldProducts;

    @OneToMany(mappedBy = "buyer")
    private List<Product> boughtProducts;

    @ManyToMany()
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> friends;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Product> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(List<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public List<Product> getBoughtProducts() {
        return this.boughtProducts;
    }

    public void setBoughtProducts(List<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public Set<User> getFriends() {
        return this.friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User newFriend) {
        if (this.getId() == newFriend.getId()) {
            return;
        }
        boolean incorrectUser = false;
        for (User currentFriend : this.friends) {
            if (currentFriend.getId() == newFriend.getId()) {
                incorrectUser = true;
                break;
            }
        }
        if (!incorrectUser) {
            this.friends.add(newFriend);
        }

    }
}
