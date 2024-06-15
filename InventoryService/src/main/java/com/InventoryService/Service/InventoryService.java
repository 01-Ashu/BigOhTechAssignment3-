package com.InventoryService.Service;


import com.InventoryService.Dto.InventoryDTO;
import com.InventoryService.Entity.Inventory;
import com.InventoryService.Repository.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryDTO> getAllInventory() {
        return inventoryRepository.findAll().stream()
                .map(inventory -> modelMapper.map(inventory, InventoryDTO.class))
                .collect(Collectors.toList());
    }

    public InventoryDTO getInventoryById(String id) {
        Inventory inventory = inventoryRepository.findById(id).orElse(null);
        return modelMapper.map(inventory, InventoryDTO.class);
    }

    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = modelMapper.map(inventoryDTO, Inventory.class);
        inventory = inventoryRepository.save(inventory);
        return modelMapper.map(inventory, InventoryDTO.class);
    }

    public InventoryDTO updateInventory(String id, InventoryDTO inventoryDTO) {
        Inventory inventory = modelMapper.map(inventoryDTO, Inventory.class);
        inventory.setId(id);
        inventory = inventoryRepository.save(inventory);
        return modelMapper.map(inventory, InventoryDTO.class);
    }

    public void deleteInventory(String id) {
        inventoryRepository.deleteById(id);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkLowInventory() {
        List<Inventory> lowInventoryItems = inventoryRepository.findByQuantityLessThanReorderLevel();
        for (Inventory item : lowInventoryItems) {
            kafkaTemplate.send("inventory-notifications", "Low inventory for product ID: " + item.getProductId());
        }
    }
}