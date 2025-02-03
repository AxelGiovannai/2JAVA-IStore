// src/main/java/store/entity/ItemEntity.java
package store.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "stock", nullable = false)
    private int stock;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private InventoryEntity inventory;

    public ItemEntity() {}

    public ItemEntity(String name, double price, int stock, InventoryEntity inventory) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.inventory = inventory;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public InventoryEntity getInventory() {
        return inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }
}