package com.apap.tugas1.repository;

import com.apap.tugas1.model.JabatanPegawaiModel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JabatanPegawaiDb extends JpaRepository<JabatanPegawaiModel, Long> {
    List<JabatanPegawaiModel> findAllByIdPegawai_Id(long id);
    List<JabatanPegawaiModel> findAllByIdJabatan_Id(long id);
}
