package com.apap.tugas1.service;


import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.repository.JabatanPegawaiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JabatanPegawaiServiceImpl implements  JabatanPegawaiService{

    @Autowired
    private JabatanPegawaiDb jabatanPegawaiDb;

    @Override
    public List<JabatanPegawaiModel> getJabatanByIdPegawai(long id) {
        return jabatanPegawaiDb.findAllByIdPegawai_Id(id);
    }

    @Override
    public List<JabatanPegawaiModel> getListJabatanPegawai(long idJabatan) {
        return jabatanPegawaiDb.findAllByIdJabatan_Id(idJabatan);
    }
}
