package com.apap.tugas1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jabatan")
public class JabatanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String namaJabatan;

    @NotNull
    @Size(max = 255)
    @Column(name = "deskripsi", nullable = false)
    private String deskripsiJabatan;

    @NotNull
    @Column(name = "gaji_pokok", nullable = false)
    private Double gajiPokok;

    @OneToMany(mappedBy = "idJabatan", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<JabatanPegawaiModel> listJabatanPegawai = new ArrayList<JabatanPegawaiModel>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public String getDeskripsiJabatan() {
        return deskripsiJabatan;
    }

    public void setDeskripsiJabatan(String deskripsiJabatan) {
        this.deskripsiJabatan = deskripsiJabatan;
    }

    public Double getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(Double gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public List<JabatanPegawaiModel> getListJabatanPegawai() {
        return listJabatanPegawai;
    }

    public void setListJabatanPegawai(List<JabatanPegawaiModel> listJabatanPegawai) {
        this.listJabatanPegawai = listJabatanPegawai;
    }
}
