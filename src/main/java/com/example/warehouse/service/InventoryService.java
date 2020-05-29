package com.example.warehouse.service;

import com.example.warehouse.model.Inventory;
import com.example.warehouse.repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    InventoryRepo inventoryRepo;

    public void save(Inventory inventory){
        inventoryRepo.save(inventory);
    }

    public void saveAll(List<Inventory> inventoryList){
        inventoryRepo.saveAll(inventoryList);
    }

    public List<Inventory> findByProductCode(String productCode){
        return inventoryRepo.findByProductCode(productCode);
    }
}
