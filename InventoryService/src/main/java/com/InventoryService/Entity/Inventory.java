package com.InventoryService.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "inventory")
public class Inventory {
    @Id
    private String id;
    private String productId;
    private int quantity;
    private int reorderLevel;


    public Inventory() {}

    public Inventory(String id, String productId, int quantity, int reorderLevel) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
}