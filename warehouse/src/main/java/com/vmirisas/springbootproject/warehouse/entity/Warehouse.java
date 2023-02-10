package com.vmirisas.springbootproject.warehouse.entity;

import com.vmirisas.springbootproject.warehouse.dto.ShelfDTO;
import com.vmirisas.springbootproject.warehouse.dto.WarehouseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "warehouse")
public class Warehouse {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "warehouse_code")
    private String warehouseCode;

    @Column(name = "description")
    private String description;

//    @JsonBackReference
    @OneToMany (//fetch = FetchType.EAGER,
                mappedBy = "warehouse",
            cascade = {
//                CascadeType.ALL
                CascadeType.DETACH,
//                CascadeType.MERGE,
                CascadeType.PERSIST,
                CascadeType.REFRESH,
                CascadeType.REMOVE
            })
    private List<Shelf> shelves = new ArrayList<>();

    // define constructors
    // define getter/setter
    // define toString
    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + warehouseId +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Warehouse(WarehouseDTO dto) {
        BeanUtils.copyProperties(dto, this);

        for (ShelfDTO s : dto.getShelves()) {
            Shelf shelf = new Shelf(s);
            shelf.setWarehouse(this);

            this.shelves.add(shelf);
        }
    }

    public void update(WarehouseDTO warehouseDTO) {
        this.warehouseCode = warehouseDTO.getWarehouseCode();
        this.description = warehouseDTO.getDescription();

    }

}
