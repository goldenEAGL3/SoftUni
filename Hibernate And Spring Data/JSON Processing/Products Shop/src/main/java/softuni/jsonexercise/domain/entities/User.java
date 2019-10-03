package softuni.jsonexercise.domain.entities;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")

public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @ManyToMany()
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> friends;

    @OneToMany(mappedBy = "buyer")
    private Set<Product> boughtProducts;

    @OneToMany(mappedBy = "seller")
    private Set<Product> soldProducts;

    public User() {
        this.friends = new HashSet<>();
    }

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

    public Set<User> getFriends() {
        return this.friends;
    }

    public void addFriend(User newFriend) {
        if (this.getId() == newFriend.getId()) {
            return;
        }
        boolean incorrectUser = false;
        for (User currentFriend : friends) {
            if (currentFriend.getId() == newFriend.getId()) {
                incorrectUser = true;
                break;
            }
        }
        if (!incorrectUser) {
            this.friends.add(newFriend);
        }
    }

    public Set<Product> getBoughtProducts() {
        return this.boughtProducts;
    }

    public void setBoughtProducts(Set<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public Set<Product> getSoldProducts() {
        return this.soldProducts;
    }

    public void setSoldProducts(Set<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
