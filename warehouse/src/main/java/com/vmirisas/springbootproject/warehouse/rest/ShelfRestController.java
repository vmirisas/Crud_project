package com.vmirisas.springbootproject.warehouse.rest;

import com.vmirisas.springbootproject.warehouse.dto.ShelfDTO;
import com.vmirisas.springbootproject.warehouse.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")
@RestController
@RequestMapping("/shelf")
public class ShelfRestController {

    @Autowired
    private ShelfService shelfService;

    // expose "/shelf" and return list of shelves
    @GetMapping("")
    public List<ShelfDTO> findAll() {
        return this.shelfService.toDtoList(this.shelfService.findAll());
    }

    //  add mapping for GET /shelf/{shelfId}
    @GetMapping("/{shelfId}")
    public ShelfDTO getShelf(@PathVariable Long shelfId) {
        return new ShelfDTO(this.shelfService.findById(shelfId));
    }

    // add mapping for POST /shelf/add = add new shelf
    @PostMapping("/add")
    public ShelfDTO addShelf(@RequestBody ShelfDTO theShelf) {
        theShelf.setShelfId(null);
        return new ShelfDTO(this.shelfService.save(theShelf));
    }

    // add mapping for PUT /shelf/update = update existing shelf
    @PutMapping("/update")
    public ShelfDTO updateShelf(@RequestBody ShelfDTO theShelf) {
        return new ShelfDTO(this.shelfService.save(theShelf));
    }

    // add mapping for DELETE /shelf/remove/{shelfId} = delete existing shelf
    @DeleteMapping("/delete/{shelfId}")
    public void deleteShelf(@PathVariable Long shelfId) {
        shelfService.deleteById(shelfId);
    }
}
