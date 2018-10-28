package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService {
    @Autowired
    private JabatanDb jabatanDb;

    @Override
    public void addJabatan(JabatanModel jabatan) {
        jabatanDb.saveAndFlush(jabatan);
    }

    @Override
    public void deleteById(long id) {
        jabatanDb.deleteById(id);
    }

    @Override
    public Optional<JabatanModel> getJabatanByName(String nama) {
        return jabatanDb.findByNamaJabatan(nama);
    }

    @Override
    public Optional<JabatanModel> getJabatanById(long id) {
        return jabatanDb.findById(id);
    }

    @Override
    public List<JabatanModel> getAllJabatan() {
        return jabatanDb.findAll();
    }
}
