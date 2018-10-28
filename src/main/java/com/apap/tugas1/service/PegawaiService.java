package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {
    Optional<PegawaiModel> getPegawaiDetailByNip(String nip);
    void addPegawai(PegawaiModel pegawai);
    PegawaiModel findTermuda(long idInstansi);
    PegawaiModel findTertua(long idInstansi);
    List<PegawaiModel> findAll();
}
