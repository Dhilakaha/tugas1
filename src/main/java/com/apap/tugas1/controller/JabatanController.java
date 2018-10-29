package com.apap.tugas1.controller;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.service.JabatanPegawaiService;
import com.apap.tugas1.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class JabatanController {
    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private JabatanPegawaiService jabatanPegawaiService;

    @RequestMapping(value = "/jabatan/tambah")
    private String addJabatan(Model model){
        JabatanModel jabatan = new JabatanModel();
        model.addAttribute("jabatan", jabatan);
        return "addJabatan";
    }

    @RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
    private String addJabatan(@ModelAttribute JabatanModel jabatan, Model model){
        jabatanService.addJabatan(jabatan);

        model.addAttribute("pesan", "Jabatan berhasil ditambahkan");
        return "updateData";
    }

    @RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
    private String dataJabatan(@RequestParam(value = "id") long id, Model model) {
        JabatanModel jabatanModel = jabatanService.getJabatanById(id).get();
        List<JabatanPegawaiModel> jumlahPegawai = jabatanPegawaiService.getListJabatanPegawai(id);

        int pegawai = jumlahPegawai.size();
        model.addAttribute("jabatan", jabatanModel);
        model.addAttribute("jmlPegawai", pegawai);
        return "dataJabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
    private String ubahJabatan(@RequestParam(value = "idJabatan") long id, Model model) {
        JabatanModel jabatanModel = jabatanService.getJabatanById(id).get();
        model.addAttribute("jabatan", jabatanModel);
        return "ubahJabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
    private String updateJabatan(@ModelAttribute JabatanModel jabatan, Model model) {
        jabatanService.addJabatan(jabatan);

        model.addAttribute("pesan", "Jabatan berhasil dirubah");
        return "updateData";
    }

    @RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
    private String viewAll(Model model) {
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();

        model.addAttribute("listAllJabatan", listJabatan);
        return "viewAll-jabatan";
    }

    @RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
    private String hapusJabatan(@RequestParam(value = "idJabatan") long id, Model model){
        List<JabatanPegawaiModel> jabatan = jabatanPegawaiService.getListJabatanPegawai(id);
        Optional<JabatanModel> objJabatan = jabatanService.getJabatanById(id);

        if(jabatan.isEmpty()) {
            jabatanService.deleteById(id);
            model.addAttribute("pesan", "Jabatan berhasil dihapus!");
            return "updateData";
        }

        model.addAttribute("pesan", "Maaf jabatan yang anda pilih memiliki pegawai");
        return "hapus-jabatan";
    }
}
