package com.vmirisas.springbootproject.warehouse.rest;

import com.vmirisas.springbootproject.warehouse.dto.WarehouseDTO;
import com.vmirisas.springbootproject.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
@RestController
@RequestMapping("/warehouse")
public class WarehouseRestController {

    @Autowired
    private WarehouseService warehouseService;

    // expose "/warehouse" and return list of warehouses
    @GetMapping("")
    public List<WarehouseDTO> findAll() {
        return this.warehouseService.toDtoList(this.warehouseService.findAll());
    }

    //  add mapping for GET /warehouse/{warehouseId}
    @GetMapping("/{warehouseId}")
    public WarehouseDTO getWarehouse(@PathVariable Long warehouseId) {
        return new WarehouseDTO(this.warehouseService.findById(warehouseId));
    }

    // add mapping for POST /warehouse/add = add new warehouse
    @PostMapping("/add")
    public WarehouseDTO addWarehouse(@RequestBody WarehouseDTO theWarehouse){
        theWarehouse.setWarehouseId(null);
        return new WarehouseDTO(this.warehouseService.save(theWarehouse));
    }

    // add mapping for PUT /warehouse/update = update existing warehouse
    @PutMapping("/update")
    public WarehouseDTO updateWarehouse(@RequestBody WarehouseDTO theWarehouse) {
        return new WarehouseDTO(this.warehouseService.update(theWarehouse));
    }

    // add mapping for DELETE /warehouse/remove/{warehouseId} = delete existing warehouse
    @DeleteMapping("/delete/{warehouseId}")
    public void deleteWarehouse(@PathVariable Long warehouseId) {
        warehouseService.deleteById(warehouseId);
    }
}
