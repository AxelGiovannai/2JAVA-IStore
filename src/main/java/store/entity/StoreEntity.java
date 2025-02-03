// src/main/java/store/entity/StoreEntity.java
package store.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stores")
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private InventoryEntity inventory;

    public StoreEntity() {}

    public StoreEntity(String name) {
        this.name = name;
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

    public InventoryEntity getInventory() {
        return inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }
}
