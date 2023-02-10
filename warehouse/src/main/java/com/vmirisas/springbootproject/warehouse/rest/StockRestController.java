package com.vmirisas.springbootproject.warehouse.rest;

import com.vmirisas.springbootproject.warehouse.dto.StockDTO;
import com.vmirisas.springbootproject.warehouse.dto.search.StockSearch;
import com.vmirisas.springbootproject.warehouse.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockRestController {

    @Autowired
    private StockService stockService;

    // expose "/stock" and return list of stocks
    @GetMapping("")
    public List<StockDTO> findAll() {
        return this.stockService.toDtoList(this.stockService.findAll());
    }

    //  add mapping for GET /stock/{stockId}
    @GetMapping("/{stockId}")
    public StockDTO getStock(@PathVariable Long stockId) {
        return new StockDTO(this.stockService.findById(stockId));
    }

    @GetMapping("/search")
    public List<StockDTO> searchStocks(StockSearch search) {
        return this.stockService.search(search);
    }

    // add mapping for GET /stock/single = find an existing stock until the given date
    @GetMapping("/single")
    public StockDTO getProductStockUntilDate(@RequestParam String barcode,
                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        return null;
    }

    // add mapping for POST /stock/add = add new stock
    @PostMapping("/add")
    public StockDTO addStock(@RequestBody StockDTO theStock) {
        // also just in case the pass an ID in JSON ... set id to null
        theStock.setStockId(null);
        return new StockDTO(this.stockService.save(theStock));
    }

    // add mapping for PUT /stock/update = update existing stock
    @PutMapping("/update")
    public StockDTO updateStock(@RequestBody StockDTO theStock) {
        return new StockDTO(this.stockService.save(theStock));
    }

    // add mapping for DELETE /stock/remove/{stockId} = delete existing stock
    @DeleteMapping("/remove/{stockId}")
    public String deleteStock(@PathVariable Long stockId) {
        stockService.deleteById(stockId);
        return "Deleted stock with id - " + stockId;
    }
}
