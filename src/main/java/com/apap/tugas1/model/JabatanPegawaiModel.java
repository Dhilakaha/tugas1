package com.apap.tugas1.model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "jabatan_pegawai")
public class JabatanPegawaiModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pegawai", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private PegawaiModel idPegawai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jabatan", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private JabatanModel idJabatan;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PegawaiModel getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(PegawaiModel idPegawai) {
        this.idPegawai = idPegawai;
    }

    public JabatanModel getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(JabatanModel idJabatan) {
        this.idJabatan = idJabatan;
    }



}
