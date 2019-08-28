package Exams.DemoExam17February2019.repository;

import java.util.LinkedHashMap;
import java.util.Map;

public class Repository {
    private Map<Integer, Person> data;
    private int id;

    public Repository() {
        this.data = new LinkedHashMap<>();
        this.id = 0;
    }

    public void add(Person person) {
        this.data.put(id++, person);
    }
    public Person get(int id) {
        return this.data.get(id);
    }
    public boolean update(int id, Person newPerson) {
        if(this.data.containsKey(id)) {
            this.data.put(id, newPerson);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        if(this.data.containsKey(id)) {
            this.data.remove(id);
            return true;
        }
        return false;
    }

    public int getCount() {
        return this.data.size();
    }

    public Map<Integer, Person> getData() {
        return this.data;
    }
}
