package com.apap.tugas1.controller;

import java.util.List;

import com.apap.tugas1.model.*;
import com.apap.tugas1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PegawaiController {

    @Autowired
    private PegawaiService pegawaiService;

    @Autowired
    private JabatanPegawaiService jabatanPegawaiService;

    @Autowired
    private JabatanService jabatanService;

    @Autowired
    private InstansiService instansiService;

    @Autowired
    private ProvinsiService provinsiService;

    @RequestMapping(value = "/")
    private String home(Model model) {
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
        List<InstansiModel> listInstansi = instansiService.getAllInstansi();

        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);
        return "home";
    }

    @RequestMapping(value = "/pegawai", method = RequestMethod.GET)
    private String dataPegawai(@RequestParam(value = "nip") String nip, Model model) {
        PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip).get();

        List<JabatanPegawaiModel> jabatanPegawai = jabatanPegawaiService.getJabatanByIdPegawai(pegawai.getId());
        InstansiModel instansi = instansiService.getInstansiById(pegawai.getIdInstansi().getId());

        int gaji = pegawaiService.hitungGaji(nip);

        model.addAttribute("pegawai", pegawai);
        model.addAttribute("instansi", instansi);
        model.addAttribute("jabatan", jabatanPegawai);
        model.addAttribute("gaji", gaji);
        return "dataPegawai";
    }

    @RequestMapping(value = "/pegawai/tambah")
    private String tambahPegawai(Model model){
        List<ProvinsiModel> listProvinsi = provinsiService.getAllPrivonsi();
        List<InstansiModel> listInstansi = instansiService.getAllInstansi();
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();

        model.addAttribute("pegawai", new PegawaiModel());

        model.addAttribute("listProvinsi", listProvinsi);
        //model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);
        return "addPegawai";
    }

    @RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
    private String addPegawai(@ModelAttribute PegawaiModel pegawai, Model model){
        String nip = "";
        String idInstansi = String.valueOf(pegawai.getIdInstansi().getId());
        String tahunMasuk = pegawai.getTahunMasuk();
        List<PegawaiModel> listPegawai = pegawaiService.findAllByInstansi(pegawai.getIdInstansi().getId());
        String digitAkhir = "0";

        String tglLahir = pegawai.getTanggalLahir().toString();
        String tahunLahir = tglLahir.substring(2,4);
        String bulanLahir = tglLahir.substring(5,7);
        String hariLahir = tglLahir.substring(8,10);

        tglLahir = hariLahir+bulanLahir+tahunLahir;

        int sama = 0 ;
        for( PegawaiModel employee : listPegawai) {
            if(pegawai.getTanggalLahir().compareTo(employee.getTanggalLahir()) == 0 && pegawai.getTahunMasuk().equals(employee.getTahunMasuk())){
                sama++;
            }
        }

        digitAkhir += Integer.toString(sama+1);

        nip = idInstansi + tglLahir + tahunMasuk + digitAkhir;
        pegawai.setNip(nip);

        System.out.println(nip);

        pegawaiService.addPegawai(pegawai);

        PegawaiModel idPegawai = pegawaiService.getPegawaiDetailByNip(nip).get();
        List<JabatanPegawaiModel> listJabatan = pegawai.getListJabatan();

        for(JabatanPegawaiModel jabatan : listJabatan) {
            jabatan.setIdPegawai(idPegawai);
            jabatanPegawaiService.addJabatanPegawai(jabatan);
        }

        model.addAttribute("pesan", "Pegawai dengan NIP " +nip+ " berhasil ditambahkan");
        return "updateData";
    }

    @RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
    private String ubahPegawai(@RequestParam(value = "nip") String nip, Model model) {
        PegawaiModel ubahPegawai = pegawaiService.getPegawaiDetailByNip(nip).get();

        InstansiModel instansi = instansiService.getInstansiById(ubahPegawai.getIdInstansi().getId());

        List<ProvinsiModel> listProvinsi = provinsiService.getAllPrivonsi();
        List<InstansiModel> listInstansi = instansiService.getAllInstansi();
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();


        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);

        model.addAttribute("pegawai",ubahPegawai );
        model.addAttribute("instansi",instansi );
        return "ubah-pegawai";
    }

    @RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
    private String hasilCari(@RequestParam(value = "provinsi", required = false) Long idprov,
                             @RequestParam(value = "instansi", required = false) Long idInst,
                             @RequestParam(value = "jabatan", required = false) Long idJabatan, Model model) {

        List<ProvinsiModel> listProvinsi = provinsiService.getAllPrivonsi();
        List<InstansiModel> listInstansi = instansiService.getAllInstansi();
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();

        List<PegawaiModel> listPegawai = pegawaiService.findAll();

        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);

        model.addAttribute("listAllPegawai", listPegawai);

        return "cari-pegawai";
    }



    @RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
    private String pegawaiTermudaTertua(@RequestParam(value = "idInstansi") long id, Model model){
        PegawaiModel pegawaitermuda = pegawaiService.findTermuda(id);
        PegawaiModel pegawaitertua = pegawaiService.findTertua(id);

        List<JabatanPegawaiModel> jabatanPegawaiMuda = jabatanPegawaiService.getJabatanByIdPegawai(pegawaitermuda.getId());
        List<JabatanPegawaiModel> jabatanPegawaiTua = jabatanPegawaiService.getJabatanByIdPegawai(pegawaitertua.getId());

        InstansiModel instansiPM = instansiService.getInstansiById(pegawaitermuda.getIdInstansi().getId());
        InstansiModel instansiPT = instansiService.getInstansiById(pegawaitertua.getIdInstansi().getId());

        int gajiTermuda = pegawaiService.hitungGaji(pegawaitermuda.getNip());
        int gajiTertua = pegawaiService.hitungGaji(pegawaitertua.getNip());

        model.addAttribute("termuda", pegawaitermuda);
        model.addAttribute("instansiPM", instansiPM);
        model.addAttribute("jabatanPM", jabatanPegawaiMuda);
        model.addAttribute("gajiTermuda", gajiTermuda);

        model.addAttribute("tertua", pegawaitertua);
        model.addAttribute("instansiPT", instansiPT);
        model.addAttribute("jabatanPT", jabatanPegawaiTua);
        model.addAttribute("gajiTertua", gajiTertua);

        return "pegawai-termuda-tertua";
    }

    @RequestMapping(value = "/pegawai/instansi", method = RequestMethod.GET)
    private @ResponseBody List<InstansiModel> instansi(@RequestParam(value = "id") int idProvinsi) {
        ProvinsiModel provinsi = provinsiService.getProvinsiById(idProvinsi);
        List<InstansiModel> instansi = instansiService.getAllInstansiByProv(provinsi);
        return instansi ;
    }

    @RequestMapping(value = "/pegawai/getjabatan", method = RequestMethod.GET)
    private @ResponseBody List<JabatanModel> getJabatan(Model model) {
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
        return listJabatan;
    }


}