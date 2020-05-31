package com.example.warehouse.configuartion;

import com.example.warehouse.dto.TransferFormDto;
import com.example.warehouse.model.Inventory;
import org.modelmapper.*;
import org.modelmapper.spi.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<TransferFormDto, Inventory> inventoryMap = new PropertyMap <TransferFormDto, Inventory>() {
            protected void configure() {
                map().setWarehouseCode(source.getToWarehouseCode());
            }
        };
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.addMappings(inventoryMap);
        return modelMapper;
    }
}
