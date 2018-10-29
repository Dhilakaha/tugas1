package com.apap.tugas1.service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;

import java.util.List;

public interface InstansiService {
    List<InstansiModel> getAllInstansi();
    List<InstansiModel> getAllInstansiByProv(ProvinsiModel prov);
    InstansiModel getInstansiById(long id);

}
