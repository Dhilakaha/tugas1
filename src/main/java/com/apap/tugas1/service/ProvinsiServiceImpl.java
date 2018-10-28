package com.apap.tugas1.service;

import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.ProvinsiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService{
    @Autowired
    private ProvinsiDb provinsiDb;


    @Override
    public List<ProvinsiModel> getAllPrivonsi() {
        return provinsiDb.findAll();
    }
}
