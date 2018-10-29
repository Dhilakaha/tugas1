package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {

    @Autowired
    private PegawaiDb pegawaiDb;

    @Autowired
    private JabatanPegawaiService jabatanPegawaiService;

    @Autowired
    private InstansiService instansiService;

    @Override
    public Optional<PegawaiModel> getPegawaiDetailByNip(String nip) {
        return pegawaiDb.findByNip(nip);
    }

    @Override
    public void addPegawai(PegawaiModel pegawai) {
        pegawaiDb.save(pegawai);
    }

    @Override
    public List<PegawaiModel> findAllByInstansi(long id) {
        return pegawaiDb.findAllByIdInstansi_Id(id);
    }

    @Override
    public PegawaiModel findTermuda(long idInstansi) {
        List<PegawaiModel> listPegawai = pegawaiDb.findAllByIdInstansi_Id(idInstansi);
        PegawaiModel pegawaiMuda = listPegawai.get(0);

        for (PegawaiModel pegawai : listPegawai) {
            if(pegawaiMuda.getTanggalLahir().compareTo(pegawai.getTanggalLahir()) < 0) {
                pegawaiMuda = pegawai;
            }
        }

        return pegawaiMuda;
    }

    @Override
    public PegawaiModel findTertua(long idInstansi) {
        List<PegawaiModel> listPegawai = pegawaiDb.findAllByIdInstansi_Id(idInstansi);
        PegawaiModel pegawaiTua = listPegawai.get(0);

        for (PegawaiModel pegawai : listPegawai) {
            if(pegawaiTua.getTanggalLahir().compareTo(pegawai.getTanggalLahir()) > 0) {
                pegawaiTua = pegawai;
            }
        }

        return pegawaiTua;
    }

    @Override
    public int hitungGaji(String nip) {
        PegawaiModel pegawai = getPegawaiDetailByNip(nip).get();

        List<JabatanPegawaiModel> jabatanPegawai = jabatanPegawaiService.getJabatanByIdPegawai(pegawai.getId());
        InstansiModel instansi = instansiService.getInstansiById(pegawai.getIdInstansi().getId());

        int gaji = 0;

        for(JabatanPegawaiModel jabatan : jabatanPegawai) {
            int temp = (int)(jabatan.getIdJabatan().getGajiPokok() + (instansi.getIdProvinsi().getPresentaseTunjangan() * jabatan.getIdJabatan().getGajiPokok()) / 100);
            if(gaji < temp){
                gaji = temp;
            }
        }
        return gaji;
    }

    @Override
    public List<PegawaiModel> findAll() {
        return pegawaiDb.findAll();
    }
}
