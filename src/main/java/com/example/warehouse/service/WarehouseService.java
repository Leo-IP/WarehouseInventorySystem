package com.example.warehouse.service;

import com.example.warehouse.model.Warehouse;
import com.example.warehouse.repository.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepo warehouseRepo;

    public List<Warehouse> findByWarehouseCodeNot(String warehouseCode){
        return warehouseRepo.findByWarehouseCodeNot(warehouseCode);
    }
}
