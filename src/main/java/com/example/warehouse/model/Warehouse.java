package com.example.warehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse extends BaseEntity implements Serializable {

    @Column(nullable = false, unique = true)
    @NaturalId
    private String warehouseCode;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @Fetch(value= FetchMode.SELECT)
    private Set<Inventory>  inventories;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof  Warehouse)) return false;
        if(!super.equals(o)) return false;

        Warehouse warehouse = (Warehouse) o;
        if(!getId().equals(warehouse.getId()));
        return getWarehouseCode().equals(warehouse.getWarehouseCode());

    }

    @Override
    public int hashCode(){
        int result = super.hashCode();
        result = 31 * result + getId().hashCode();
        result = 31 * result + getWarehouseCode().hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "Warehouse{" +
                "id ='" + getId() + '\'' +
                ", warehouseCode='" + warehouseCode + '\'' +
                '}';
    }
}
