package com.apap.tugas1.service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InstansiServiceImpl implements  InstansiService {

    @Autowired
    private InstansiDb instansiDb;


    @Override
    public List<InstansiModel> getAllInstansi() {

        return instansiDb.findAll();
    }

    @Override
    public List<InstansiModel> getAllInstansiByProv(ProvinsiModel prov) {

        return instansiDb.findAllByIdProvinsi(prov);
    }

    @Override
    public InstansiModel getInstansiById(long id) {
        return instansiDb.getOne(id) ;
    }
}
