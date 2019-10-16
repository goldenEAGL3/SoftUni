package domain.entity;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    protected BaseEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
