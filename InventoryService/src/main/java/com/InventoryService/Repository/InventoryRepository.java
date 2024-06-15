package com.InventoryService.Repository;

import com.InventoryService.Entity.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Arrays;
import java.util.List;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    List<Inventory> findByQuantityLessThanReorderLevel();


}