package viceCity.repositories;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GunRepository implements Repository<Gun> {
    private Map<String, Gun> guns;

    public GunRepository() {
        this.guns = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(this.guns.values().stream().collect(Collectors.toList()));
    }

    @Override
    public void add(Gun model) {
        if (!this.guns.containsKey(model.getName())) {
            this.guns.put(model.getName(), model);
        }
    }

    @Override
    public boolean remove(Gun model) {
        Gun gun = this.guns.remove(model.getName());
        return gun != null;
    }

    @Override
    public Gun find(String name) {
        return this.guns.get(name);
    }
}
