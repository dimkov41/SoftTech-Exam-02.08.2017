package shoppinglist.entity;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(nullable = false)
    private int priority;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public int quantity;

    @Column(nullable = false)
    public String status;

    public int getId() {
        return id;
    }

    @Required
    public void setId(int id) {
        this.id = id;
    }

    public int getPriority() {
        return priority;
    }

    @Required
    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    @Required
    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    @Required
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    @Required
    public void setStatus(String status) {
        this.status = status;
    }
}
