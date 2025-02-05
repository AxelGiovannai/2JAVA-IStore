package store.entity;

import jakarta.persistence.*;
import user.entity.UserEntity;

import java.util.List;

/**
 * Represents a store entity.
 */
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

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> employees;

    public StoreEntity() {}

    public StoreEntity(String name) {
        this.name = name;
    }

    /**
     * Gets the ID of the store.
     *
     * @return the ID of the store
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the store.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the store.
     *
     * @return the name of the store
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the store.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the inventory associated with the store.
     *
     * @return the inventory associated with the store
     */
    public InventoryEntity getInventory() {
        return inventory;
    }

    /**
     * Sets the inventory associated with the store.
     *
     * @param inventory the inventory to set
     */
    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }

    /**
     * Gets the list of employees associated with the store.
     *
     * @return the list of employees associated with the store
     */
    public List<UserEntity> getEmployees() {
        return employees;
    }

    /**
     * Sets the list of employees associated with the store.
     *
     * @param employees the list of employees to set
     */
    public void setEmployees(List<UserEntity> employees) {
        this.employees = employees;
    }
}