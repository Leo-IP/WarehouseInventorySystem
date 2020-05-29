package com.example.warehouse.repository;

import com.example.warehouse.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    List<Inventory> findByProductCode(String productCode);
}
