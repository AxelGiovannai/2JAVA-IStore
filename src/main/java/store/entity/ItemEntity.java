package store.entity;

import jakarta.persistence.*;

/**
 * Represents an item entity.
 */
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

    /**
     * Gets the ID of the item.
     *
     * @return the ID of the item
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the item.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the item.
     *
     * @return the price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the item.
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the stock of the item.
     *
     * @return the stock of the item
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock of the item.
     *
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the inventory associated with the item.
     *
     * @return the inventory associated with the item
     */
    public InventoryEntity getInventory() {
        return inventory;
    }

    /**
     * Sets the inventory associated with the item.
     *
     * @param inventory the inventory to set
     */
    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }
}