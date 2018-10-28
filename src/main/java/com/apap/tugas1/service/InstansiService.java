package com.apap.tugas1.service;

import com.apap.tugas1.model.InstansiModel;

import java.util.List;

public interface InstansiService {
    List<InstansiModel> getAllInstansi();
    List<InstansiModel> getAllInstansiByProv(int id);
    InstansiModel getInstansiById(long id);

}
