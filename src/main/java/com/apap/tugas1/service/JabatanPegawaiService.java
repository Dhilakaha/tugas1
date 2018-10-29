package com.apap.tugas1.service;

import com.apap.tugas1.model.JabatanPegawaiModel;

import java.util.List;
import java.util.Optional;

public interface JabatanPegawaiService{
    List<JabatanPegawaiModel> getJabatanByIdPegawai(long id);
    List<JabatanPegawaiModel> getListJabatanPegawai(long idJabatan);
    void addJabatanPegawai(JabatanPegawaiModel jabatanPegawai);
}
