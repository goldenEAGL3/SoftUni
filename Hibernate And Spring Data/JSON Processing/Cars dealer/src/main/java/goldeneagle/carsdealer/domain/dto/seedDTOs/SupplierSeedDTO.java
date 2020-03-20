package goldeneagle.carsdealer.domain.dto.seedDTOs;

import com.google.gson.annotations.Expose;

public class SupplierSeedDTO {

    @Expose
    private String name;
    @Expose
    private boolean isImporter;

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
}
