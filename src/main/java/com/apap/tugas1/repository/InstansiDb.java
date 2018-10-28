package com.apap.tugas1.repository;

import com.apap.tugas1.model.InstansiModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstansiDb extends JpaRepository<InstansiModel, Long> {

    List<InstansiModel> getAllByIdProvinsi_Id(int id);

}
