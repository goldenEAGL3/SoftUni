package goldeneagle.carsdealer.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {


    @Column(nullable = false)
    private String name;
    @Column(name = "is_importer", nullable = false)
    private boolean isImporter;

    @OneToMany(mappedBy = "supplier")
    private List<Part> parts;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return this.isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }

    public List<Part> getParts() {
        return this.parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
