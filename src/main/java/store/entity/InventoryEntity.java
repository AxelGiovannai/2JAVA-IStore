// src/main/java/store/entity/InventoryEntity.java
package store.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "inventories")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StoreEntity store;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> items;

    public InventoryEntity() {}

    public InventoryEntity(StoreEntity store) {
        this.store = store;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}