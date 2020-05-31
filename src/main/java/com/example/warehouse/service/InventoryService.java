package com.example.warehouse.service;

import com.example.warehouse.dto.TransferFormDto;
import com.example.warehouse.model.Inventory;
import com.example.warehouse.repository.InventoryRepo;
import com.example.warehouse.repository.ProductRepo;
import com.example.warehouse.repository.WarehouseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    WarehouseRepo warehouseRepo;
    @Autowired
    InventoryRepo inventoryRepo;
    @Autowired
    ModelMapper modelMapper;

    public void saveTransferData(TransferFormDto transferFormDto){
        Inventory fromInventory = inventoryRepo.findByProductCodeAndAndWarehouseCode(transferFormDto.getProductCode(), transferFormDto.getFromWarehouseCode());
        if(fromInventory != null){
            int newQty = fromInventory.getQty() - transferFormDto.getQty();
            fromInventory.setQty(newQty);
            inventoryRepo.save(fromInventory);
            boolean isInventoryRecordExists = inventoryRepo.existsByProductCodeAndWarehouseCode(transferFormDto.getProductCode(), transferFormDto.getToWarehouseCode());
            if(isInventoryRecordExists){
                Inventory toInventory = inventoryRepo.findByProductCodeAndAndWarehouseCode(transferFormDto.getProductCode(), transferFormDto.getToWarehouseCode());
                int qty = toInventory.getQty() + transferFormDto.getQty();
                toInventory.setQty(qty);
                System.out.println("toInventory: " + toInventory);
                inventoryRepo.save(toInventory);
            }else{
                Inventory newInventoryRecord = modelMapper.map(transferFormDto, Inventory.class);
                newInventoryRecord.setProduct(null);
                newInventoryRecord.setWarehouse(null);
                System.out.println("newInventoryRecord: " + newInventoryRecord);
                inventoryRepo.save(newInventoryRecord);
            }
        }
    }

    public void saveAll(List<Inventory> inventoryList){
        for(Inventory inventory : inventoryList){
            Inventory existRecord = inventoryRepo.findByProductCodeAndAndWarehouseCode(inventory.getProductCode(), inventory.getWarehouseCode());
            if(existRecord != null){
                existRecord.setQty(inventory.getQty() + existRecord.getQty());
                inventoryRepo.save(existRecord);
            }else{
                inventoryRepo.save(inventory);
            }
        }
//        inventoryRepo.saveAll(inventoryList);
    }

    public List<Inventory> findByProductCode(String productCode) {
        return inventoryRepo.findByProductCode(productCode);
    }

//    public Inventory findByProductCodeAndWarehouseCode(String productCode, String warehouseCode){
//       return inventoryRepo.findByProductCodeAndAndWarehouseCode(productCode, warehouseCode);
//        return inventoryRepo.findAllByProductCodeAndAndWarehouseCode(productCode, warehouseCode).get(0);
//    }
//
//    public List<Inventory> findByProductCodeAndWarehouseCodeNot(String productCode, String warehouseCode){
//        return inventoryRepo.findByProductCodeAndWarehouseCodeNot(productCode, warehouseCode);
//    }
//
//    public boolean existsByProductCodeAndWarehouseCode(String productCode, String warehouseCode){
//        return inventoryRepo.existsByProductCodeAndWarehouseCode(productCode, warehouseCode);
//    }
//
//    public void createNewInventoryRecord(TransferFormDto transferFormDto){
//
//
//        Inventory inventoryRecord = modelMapper.map(transferFormDto, Inventory.class);
//        System.out.println(inventoryRecord);
//
//    }
//    public void saveInventoryCSVFileData(Inventory inventory){
//        Inventory existRecord = inventoryRepo.findByProductCodeAndAndWarehouseCode(inventory.getProductCode(), inventory.getWarehouseCode());
//        if(existRecord != null){
//            existRecord.setQty(inventory.getQty() + existRecord.getQty());
//            inventoryRepo.save(existRecord);
//        }else{
//            inventoryRepo.save(inventory);
//        }
//
//     }
//
//    public void save(Inventory inventory){
//        inventoryRepo.save(inventory);
//    }
}
