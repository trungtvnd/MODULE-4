package cg.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;
    private String c_name;

    public Category() {
    }

    public int getId() {
        return c_id;
    }

    public void setId(int c_id) {
        this.c_id = c_id;
    }

    public String getName() {
        return c_name;
    }

    public void setName(String c_name) {
        this.c_name = c_name;
    }
}
