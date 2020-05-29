package com.example.warehouse.controller;

import com.example.warehouse.model.Inventory;
import com.example.warehouse.model.Product;
import com.example.warehouse.service.InventoryService;
import com.example.warehouse.service.ProductService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.nio.Buffer;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryService inventoryService;

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

    @PostMapping("/upload-product-csv-file")
    public String uploadProductCSVFile(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes){
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a CSV file to upload");
        } else {
            try{
                List<Product> productList = parseCSVWithHeader(Product.class, file);
                productService.saveAll(productList);
                redirectAttributes.addFlashAttribute("message", "The file has been successfully uploaded.");
            }catch (Exception e){
                redirectAttributes.addFlashAttribute("message", "An error occurred while processing the CSV file.");
            }
//            try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
//
//                CsvToBean<Product> csvToBean = new CsvToBeanBuilder(reader)
//                        .withType(Product.class)
//                        .withIgnoreLeadingWhiteSpace(true)
//                        .build();
//
//                List<Product> products = csvToBean.parse();
//                productService.saveAll(products);
////                for(Product product : products){
////                    productService.save(product);
////                }
//
//                redirectAttributes.addFlashAttribute("message", "The file has been successfully uploaded.");
//
//            }catch(Exception e){
//                redirectAttributes.addFlashAttribute("message", "An error occurred while processing the CSV file.");
//            }
        }
        return "redirect:/product";
    }

    @PostMapping("/upload-inventory-csv-file")
    public String uploadInventoryCSVFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a CSV file to upload");
        }else{
            try{
                List<Inventory> inventoryList = parseCSVWithHeader(Inventory.class, file);
                inventoryService.saveAll(inventoryList);
                redirectAttributes.addFlashAttribute("message", "The file has been successfully uploaded.");
            }catch (Exception e){
                redirectAttributes.addFlashAttribute("message", "An error occurred while processing the CSV file.");
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
