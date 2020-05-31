package com.example.warehouse.repository;

import com.example.warehouse.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {

    List<Warehouse> findByWarehouseCodeNot (String warehouseCode);

//    Warehouse findByWarehouseCode(String warehouseCode);
}
