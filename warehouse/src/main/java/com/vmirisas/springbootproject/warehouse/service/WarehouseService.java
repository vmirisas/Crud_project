package com.vmirisas.springbootproject.warehouse.service;

import com.vmirisas.springbootproject.warehouse.dto.WarehouseDTO;
import com.vmirisas.springbootproject.warehouse.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    List<Warehouse> findAll();
    Warehouse findById (Long theId);
    Warehouse save(WarehouseDTO theWarehouse);

    Warehouse update(WarehouseDTO theWarehouse);

    void deleteById(Long theId);
    List<WarehouseDTO> toDtoList(List<Warehouse> warehouses);
}
