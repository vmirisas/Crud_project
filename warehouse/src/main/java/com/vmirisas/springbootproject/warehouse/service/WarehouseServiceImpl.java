package com.vmirisas.springbootproject.warehouse.service;

import com.vmirisas.springbootproject.warehouse.dto.WarehouseDTO;
import com.vmirisas.springbootproject.warehouse.entity.Warehouse;
import com.vmirisas.springbootproject.warehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService{

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> findAll(){
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse findById(Long theId) {
        return this.warehouseRepository.findById(theId).orElseThrow(() -> new RuntimeException("Warehouse with id '" + theId + "' not found"));
    }

    @Override
    public Warehouse save(WarehouseDTO theWarehouse) {
        return warehouseRepository.save(new Warehouse(theWarehouse));
    }

    @Override
    public Warehouse update(WarehouseDTO theWarehouse) {
        Warehouse warehouseToUpdate = findById(theWarehouse.getWarehouseId());
        warehouseToUpdate.update(theWarehouse);
        return warehouseRepository.save(warehouseToUpdate);
    }

    @Override
    public void deleteById(Long theId) {
        Warehouse warehouse = this.findById(theId);
        warehouseRepository.delete(warehouse);
    }

    @Override
    public List<WarehouseDTO> toDtoList(List<Warehouse> warehouses) {
        List<WarehouseDTO> list = new ArrayList<>();
        for (Warehouse w : warehouses) {
            list.add(new WarehouseDTO(w));
        }
        return list;
    }
}
