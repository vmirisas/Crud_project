package com.vmirisas.springbootproject.warehouse.service;

import com.vmirisas.springbootproject.warehouse.dto.FormDetailDTO;
import com.vmirisas.springbootproject.warehouse.entity.FormDetail;
import com.vmirisas.springbootproject.warehouse.repository.FormDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FormDetailServiceImpl implements FormDetailService{

    @Autowired
    private FormDetailRepository formDetailRepository;

    @Override
    public List<FormDetailDTO> findAll() {
        List<FormDetail> formDetailList = formDetailRepository.findAll();
        List<FormDetailDTO> formDetailDTOList = new ArrayList<>();

        for (FormDetail detail: formDetailList) {
            formDetailDTOList.add(new FormDetailDTO(detail));
        }

        return formDetailDTOList;
    }

    @Override
    public FormDetailDTO findById(Long theId) {
       Optional<FormDetail> result = formDetailRepository.findById(theId);

       FormDetailDTO theFormDetail;

       if(result.isPresent()) {
           theFormDetail = new FormDetailDTO(result.get());
       } else {
           throw new RuntimeException("Did not find Form Detail id " + theId);
       }

       return theFormDetail;
    }

    @Override
    public void save(FormDetailDTO theFormDetail) {
        formDetailRepository.save(new FormDetail(theFormDetail));
    }

    @Override
    public void deleteById(Long theId) {
        formDetailRepository.deleteById(theId);
    }
}