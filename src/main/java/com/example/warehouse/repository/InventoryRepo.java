package com.example.warehouse.repository;

import com.example.warehouse.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    List<Inventory> findByProductCode(String productCode);

    Inventory findByProductCodeAndAndWarehouseCode(String productCode, String warehouseCode);

    boolean existsByProductCodeAndWarehouseCode(String productCode, String warehouseCode);

//    List<Inventory> findByProductCodeAndWarehouseCodeNot(String productCode, String warehouseCode);
//    List<Inventory> findAllByProductCodeAndAndWarehouseCode(String productCode, String warehouseCode);
}
