package com.example.warehouse.controller;

import com.example.warehouse.dto.TransferFormDto;
import com.example.warehouse.model.Inventory;
import com.example.warehouse.model.Product;
import com.example.warehouse.model.Warehouse;
import com.example.warehouse.service.InventoryService;
import com.example.warehouse.service.ProductService;
import com.example.warehouse.service.WarehouseService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private WarehouseService warehouseService;


    @GetMapping("/")
    public String Index(){
        return "index";
    }

    @GetMapping("/product")
    public String productView(@ModelAttribute("message") String message){
        return "product";
    }

    @GetMapping("/inventory")
    public String inventoryView(@ModelAttribute("message") String message) {return "inventory";}

    @GetMapping("/search")
    public String searchView(@ModelAttribute("message") String message) {return "search";}

    @GetMapping("/transfer")
    public String transferView(@ModelAttribute("transferFormDto") TransferFormDto transferFormDto, @RequestParam("qty") int qty, Model model){

        List<Warehouse> warehouseList = warehouseService.findByWarehouseCodeNot(transferFormDto.getFromWarehouseCode());
        model.addAttribute("warehouseList", warehouseList);
        model.addAttribute("qty", qty);
        return "transfer";
    }

    @PostMapping("/upload-product-csv-file")
    public String uploadProductCSVFile(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes){
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a CSV file to upload");
        } else {
            try {
                List<Product> productList = parseCSVWithHeader(Product.class, file);
                for (Product product : productList) {
                    if (product.getProductName().trim().isEmpty()) throw new Exception();
                    if (product.getProductCode().trim().isEmpty()) throw new Exception();
                    if (product.getWeight().intValue() < 0) throw new Exception();
                }
                productService.saveAll(productList);
                redirectAttributes.addFlashAttribute("message", "The file has been successfully uploaded.");
            }catch (Exception e){
                redirectAttributes.addFlashAttribute("message", "An error occurred while processing the CSV file. Please check the data in the csv file!");
            }
        }
        return "redirect:/product";
    }

    @PostMapping("/upload-inventory-csv-file")
    public String uploadInventoryCSVFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a CSV file to upload");
        }else{
            try {
                List<Inventory> inventoryList = parseCSVWithHeader(Inventory.class, file);
                for (Inventory inventory : inventoryList) {
                    if (inventory.getQty() < 0) throw new Exception();
                }
                inventoryService.saveAll(inventoryList);
                redirectAttributes.addFlashAttribute("message", "The file has been successfully uploaded.");
            }catch (Exception e){
                redirectAttributes.addFlashAttribute("message", "An error occurred while processing the CSV file. Please check the data in the csv file!");
            }
        }
        return "redirect:/inventory";
    }

    @GetMapping("/search/product")
    public String searchByProductCode(@RequestParam("product_code") String productCode, Model model){
        List<Inventory> inventoryList = inventoryService.findByProductCode(productCode);
        if(inventoryList.size() < 1){
            model.addAttribute("message", "Product does not exist in inventory");
            model.addAttribute("error", true);
        }else{
            model.addAttribute("inventoryList", inventoryList);
            model.addAttribute("status", true);
        }

        return "search";
    }

    @PostMapping("/transfer")
    public String transferProduct(@ModelAttribute("transferFormDto") TransferFormDto transferFormDto) {
        inventoryService.saveTransferData(transferFormDto);
        return "redirect:/search/product?product_code=" + transferFormDto.getProductCode();
    }

    public static <T> List<T> parseCSVWithHeader(Class<T> tClass, MultipartFile file) throws IOException{
        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withType(tClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<T> objs = csvToBean.parse();
            return objs;
        }
    }
}
