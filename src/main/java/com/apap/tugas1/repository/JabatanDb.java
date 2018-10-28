package com.apap.tugas1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas1.model.JabatanModel;

import java.util.Optional;

@Repository
public interface JabatanDb extends JpaRepository<JabatanModel, Long>{
    Optional<JabatanModel> findByNamaJabatan(String namaJabatan);
    Optional<JabatanModel> findById(long id);
}
