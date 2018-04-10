package com.jking.computersite.service.Impl;

import com.jking.computersite.entity.Catalogue;
import com.jking.computersite.mapper.CatalogueMapper;
import com.jking.computersite.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogueServiceImpl implements CatalogueService {

    @Autowired
    private CatalogueMapper catalogueMapper;

    @Override
    public void add(Catalogue catalogue) {

        catalogueMapper.insert(catalogue);
    }
}
