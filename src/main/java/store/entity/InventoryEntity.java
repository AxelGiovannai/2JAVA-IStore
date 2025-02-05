package store.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Represents an inventory entity.
 */
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

    /**
     * Gets the ID of the inventory.
     *
     * @return the ID of the inventory
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the inventory.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the store associated with the inventory.
     *
     * @return the store associated with the inventory
     */
    public StoreEntity getStore() {
        return store;
    }

    /**
     * Sets the store associated with the inventory.
     *
     * @param store the store to set
     */
    public void setStore(StoreEntity store) {
        this.store = store;
    }

    /**
     * Gets the list of items in the inventory.
     *
     * @return the list of items in the inventory
     */
    public List<ItemEntity> getItems() {
        return items;
    }

    /**
     * Sets the list of items in the inventory.
     *
     * @param items the list of items to set
     */
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }
}