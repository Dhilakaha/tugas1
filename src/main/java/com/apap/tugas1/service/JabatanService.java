package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanModel;

import java.util.List;
import java.util.Optional;

public interface JabatanService {
    void addJabatan(JabatanModel jabatan);
    void deleteById(long id);
    Optional<JabatanModel> getJabatanByName(String nama);
    Optional<JabatanModel> getJabatanById(long id);
    List<JabatanModel> getAllJabatan();
}
