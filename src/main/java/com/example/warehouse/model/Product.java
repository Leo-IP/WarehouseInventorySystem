package com.example.warehouse.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindAndJoinByPosition;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity implements Serializable {

    @Column(nullable = false)
    @CsvBindByName(column = "Product_Name")
//    @CsvBindByPosition(position = 0)
    private String productName;

    @Column(nullable = false, unique = true)
    @NaturalId
    @CsvBindByName(column = "Code")
    private String productCode;

    @CsvBindByName(column = "Weight")
    @Type(type = "big_decimal")
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal weight;

    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL)
    @Fetch(value= FetchMode.SELECT)
    private Set<Inventory> inventories;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof  Product)) return false;
        if(!super.equals(o)) return false;

        Product product = (Product) o;
        if(!getId().equals(product.getId()));
        if(!getProductName().equals(product.getProductName())) return false;
        if(!getProductCode().equals(product.getProductCode())) return false;
        return getWeight().equals(product.getWeight());

    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = 31 * result + getId().hashCode();
        result = 31 * result + getProductName().hashCode();
        result = 31 * result + getProductCode().hashCode();
        result = 31 * result + getWeight().hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "Product{" +
                "id ='" + getId() + '\'' +
                ", productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                "' weight='" + weight +'\'' +
                '}';
    }

}
