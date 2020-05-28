package com.example.warehouse.controller;

import com.example.warehouse.model.Product;
import com.example.warehouse.service.ProductService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String Index(){
        return "index";
    }

    @GetMapping("/product")
    public String product(@ModelAttribute("message") String message){
        return "product";
    }

    @PostMapping("/upload-product-csv-file")
    public String uploadProductCSVFile(@RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes){
        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Please select a CSV file to upload");
        } else {
            try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){

                CsvToBean<Product> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Product.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                List<Product> products = csvToBean.parse();
                productService.saveAll(products);
//                for(Product product : products){
//                    productService.save(product);
//                }

                redirectAttributes.addFlashAttribute("message", "The file has been successfully uploaded.");

            }catch(Exception e){
                redirectAttributes.addAttribute("message", "An error occurred while processing the CSV file.");
            }
        }

        return "redirect:/product";
    }


}
