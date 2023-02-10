package com.vmirisas.springbootproject.warehouse.rest;

import com.vmirisas.springbootproject.warehouse.dto.FormDetailDTO;
import com.vmirisas.springbootproject.warehouse.service.FormDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formDetail")
public class FormDetailRestController {

    @Autowired
    private FormDetailService formDetailService;

    // expose "/formDetail" and return list of form details
    @GetMapping("")
    public List<FormDetailDTO> findAll() {
        return this.formDetailService.toDtoList(this.formDetailService.findAll());
    }

    //  add mapping for GET /formDetail/{formDetailId}
    @GetMapping("/{formDetailId}")
    public FormDetailDTO getFormDetail(@PathVariable Long formDetailId) {
        return new FormDetailDTO(this.formDetailService.findById(formDetailId));
    }

    // add mapping for POST /formDetail/add = add new form detail
    @PostMapping("/add")
    public FormDetailDTO addFormDetail(@RequestBody FormDetailDTO theFormDetail) {
        // also just in case the pass an ID in JSON ... set id to null
        theFormDetail.setFormDetailId(null);
        return new FormDetailDTO(this.formDetailService.save(theFormDetail));
    }

    // add mapping for PUT /formDetail/update = update existing form detail
    @PutMapping("/update")
    public FormDetailDTO updateFormDetailDTO(@RequestBody FormDetailDTO theFormDetail) {
        return new FormDetailDTO(this.formDetailService.save(theFormDetail));
    }

    // add mapping for DELETE /formDetail/remove/{formDetailId} = delete existing form detailId
    @DeleteMapping("/remove/{formDetailId}")
    public String deleteFormDetail(@PathVariable Long formDetailId) {
        formDetailService.deleteById(formDetailId);
        return "Deleted Form Detail id - " + formDetailId;
    }
}
