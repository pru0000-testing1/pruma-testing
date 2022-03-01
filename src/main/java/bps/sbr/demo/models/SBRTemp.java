package bps.sbr.demo.models;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sbr_temps")
public class SBRTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CsvBindByName(column = "nmperusahaan")
    @Column(columnDefinition = "nvarchar")
    private String nmperusahaan;
    @CsvBindByName(column = "alamat")
    @Column(columnDefinition = "nvarchar")
    private String alamat;
    @CsvBindByName(column = "kddesa")
    @Column(columnDefinition = "nvarchar")
    private String kddesa;
    @CsvBindByName(column = "kdkec")
    @Column(columnDefinition = "nvarchar")
    private String kdkec;
    @CsvBindByName(column = "kdkab")
    @Column(columnDefinition = "nvarchar")
    private String kdkab;
    @CsvBindByName(column = "kdprop")
    @Column(columnDefinition = "nvarchar")
    private String kdprop;
    @CsvBindByName(column = "kodepos")
    @Column(columnDefinition = "nvarchar")
    private String kodepos;
    @CsvBindByName(column = "kodearea")
    @Column(columnDefinition = "nvarchar")
    private String kodearea;
    @CsvBindByName(column = "notelp")
    @Column(columnDefinition = "nvarchar")
    private String notelp;
    @CsvBindByName(column = "noektensi")
    @Column(columnDefinition = "nvarchar")
    private String noekstensi;
    @CsvBindByName(column = "nofax")
    @Column(columnDefinition = "nvarchar")
    private String nofax;
    @CsvBindByName(column = "email")
    @Column(columnDefinition = "nvarchar")
    private String email;
    @CsvBindByName(column = "cpnama")
    @Column(columnDefinition = "nvarchar")
    private String cpnama;
    @CsvBindByName(column = "cpnotelp")
    @Column(columnDefinition = "nvarchar")
    private String cpnotelp;
    @CsvBindByName(column = "kegutama")
    @Column(columnDefinition = "nvarchar")
    private String kegutama;
    @CsvBindByName(column = "kdkategori")
    @Column(columnDefinition = "nvarchar")
    private String kdkategori;
    @CsvBindByName(column = "kdkbli")
    @Column(columnDefinition = "nvarchar")
    private String kdkbli;
    @CsvBindByName(column = "badanhukum")
    @Column(columnDefinition = "nvarchar")
    private String badanhukum;
    @CsvBindByName(column = "produk")
    @Column(columnDefinition = "nvarchar")
    private String produk;
    @CsvBindByName(column = "jkpengusaha")
    @Column(columnDefinition = "nvarchar")
    private String jkpengusaha;
    @CsvBindByName(column = "pendapatan_per_tahun")
    private long pendapatanPerTahun;
    @CsvBindByName(column = "totaltk")
    private int totaltk;
    @CsvBindByName(column = "output")
    private int output;
    @CsvBindByName(column = "status_perusahaan")
    @Column(columnDefinition = "nvarchar")
    private String statusPerusahaan;
    private int aset;
    @Column(columnDefinition = "nvarchar")
    @CsvBindByName(column = "institusi")
    private String institusi;
    @CsvBindByName(column = "sm")
    @Column(columnDefinition = "nvarchar")
    private String SM;
    @CsvBindByName(column = "kegiatan")
    @Column(columnDefinition = "nvarchar")
    private String kegiatan;
    @CsvBindByName(column = "tahun")
    @Column(columnDefinition = "nvarchar")
    private String tahun;
    private int petugas;
    private Date isLocked;
    @Column(columnDefinition = "nvarchar")
    private String idsbr;
    private int uploadedPetugas;
    private Date uploadedDatetime;
    @Column(columnDefinition = "nvarchar")
    private String uploadedFilename;

    public void setPendapatan_per_tahun(long pendapatanPerTahun) {
        this.pendapatanPerTahun = pendapatanPerTahun;
    }

    public int getUploadedPetugas() {
        return uploadedPetugas;
    }

    public void setUploadedPetugas(int uploadedPetugas) {
        this.uploadedPetugas = uploadedPetugas;
    }

    public Date getUploadedDatetime() {
        return uploadedDatetime;
    }

    public void setUploadedDatetime(Date uploadedDatetime) {
        this.uploadedDatetime = uploadedDatetime;
    }

    public String getUploadedFilename() {
        return uploadedFilename;
    }

    public void setUploadedFilename(String uploadedFilename) {
        this.uploadedFilename = uploadedFilename;
    }



    public int getTotaltk() {
        return totaltk;
    }

    public void setTotaltk(int totaltk) {
        this.totaltk = totaltk;
    }

    public SBRTemp() {
    }

    public SBRTemp(String nmperusahaan, String alamat, String kddesa, String kdkec, String kdkab, String kdprop, String kodepos, String kodearea, String notelp, String noekstensi, String nofax, String email, String cpnama, String cpnotelp, String kegutama, String kdkategori, String kdkbli, String badanhukum, String produk, String jkpengusaha, int pendapatanPerTahun, int totaltk, int output, String statusPerusahaan, int aset, String institusi, String SM, String kegiatan, String tahun, int petugas, Date isLocked, String idsbr) {
        this.nmperusahaan = nmperusahaan;
        this.alamat = alamat;
        this.kddesa = kddesa;
        this.kdkec = kdkec;
        this.kdkab = kdkab;
        this.kdprop = kdprop;
        this.kodepos = kodepos;
        this.kodearea = kodearea;
        this.notelp = notelp;
        this.noekstensi = noekstensi;
        this.nofax = nofax;
        this.email = email;
        this.cpnama = cpnama;
        this.cpnotelp = cpnotelp;
        this.kegutama = kegutama;
        this.kdkategori = kdkategori;
        this.kdkbli = kdkbli;
        this.badanhukum = badanhukum;
        this.produk = produk;
        this.jkpengusaha = jkpengusaha;
        this.pendapatanPerTahun = pendapatanPerTahun;
        this.totaltk = totaltk;
        this.output = output;
        this.statusPerusahaan = statusPerusahaan;
        this.aset = aset;
        this.institusi = institusi;
        this.SM = SM;
        this.kegiatan = kegiatan;
        this.tahun = tahun;
        this.petugas = petugas;
        this.isLocked = isLocked;
        this.idsbr = idsbr;
    }

    public Long getId() {
        return id;
    }

    public String getNmperusahaan() {
        return nmperusahaan;
    }

    public void setNmperusahaan(String nmperusahaan) {
        this.nmperusahaan = nmperusahaan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKddesa() {
        return kddesa;
    }

    public void setKddesa(String kddesa) {
        this.kddesa = kddesa;
    }

    public String getKdkec() {
        return kdkec;
    }

    public void setKdkec(String kdkec) {
        this.kdkec = kdkec;
    }

    public String getKdkab() {
        return kdkab;
    }

    public void setKdkab(String kdkab) {
        this.kdkab = kdkab;
    }

    public String getKdprop() {
        return kdprop;
    }

    public void setKdprop(String kdprop) {
        this.kdprop = kdprop;
    }

    public String getKodepos() {
        return kodepos;
    }

    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
    }

    public String getKodearea() {
        return kodearea;
    }

    public void setKodearea(String kodearea) {
        this.kodearea = kodearea;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getNoekstensi() {
        return noekstensi;
    }

    public void setNoekstensi(String noekstensi) {
        this.noekstensi = noekstensi;
    }

    public String getNofax() {
        return nofax;
    }

    public void setNofax(String nofax) {
        this.nofax = nofax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpnama() {
        return cpnama;
    }

    public void setCpnama(String cpnama) {
        this.cpnama = cpnama;
    }

    public String getCpnotelp() {
        return cpnotelp;
    }

    public void setCpnotelp(String cpnotelp) {
        this.cpnotelp = cpnotelp;
    }

    public String getKegutama() {
        return kegutama;
    }

    public void setKegutama(String kegutama) {
        this.kegutama = kegutama;
    }

    public String getKdkategori() {
        return kdkategori;
    }

    public void setKdkategori(String kdkategori) {
        this.kdkategori = kdkategori;
    }

    public String getKdkbli() {
        return kdkbli;
    }

    public void setKdkbli(String kdkbli) {
        this.kdkbli = kdkbli;
    }

    public String getBadanhukum() {
        return badanhukum;
    }

    public void setBadanhukum(String badanhukum) {
        this.badanhukum = badanhukum;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getJkpengusaha() {
        return jkpengusaha;
    }

    public void setJkpengusaha(String jkpengusaha) {
        this.jkpengusaha = jkpengusaha;
    }

    public long getPendapatanPerTahun() {
        return pendapatanPerTahun;
    }

    public void setPendapatanPerTahun(int pendapatanPerTahun) {
        this.pendapatanPerTahun = pendapatanPerTahun;
    }


    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public String getStatusPerusahaan() {
        return statusPerusahaan;
    }

    public void setStatusPerusahaan(String statusPerusahaan) {
        this.statusPerusahaan = statusPerusahaan;
    }

    public int getAset() {
        return aset;
    }

    public void setAset(int aset) {
        this.aset = aset;
    }

    public String getInstitusi() {
        return institusi;
    }

    public void setInstitusi(String institusi) {
        this.institusi = institusi;
    }

    public String getSM() {
        return SM;
    }

    public void setSM(String SM) {
        this.SM = SM;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public int getPetugas() {
        return petugas;
    }

    public void setPetugas(int petugas) {
        this.petugas = petugas;
    }

    public Date getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Date isLocked) {
        this.isLocked = isLocked;
    }

    public String getIdsbr() {
        return idsbr;
    }

    public void setIdsbr(String idsbr) {
        this.idsbr = idsbr;
    }

    public String getSBRTempName(){
        return this.nmperusahaan +" "+this.alamat + " " + this.kegiatan + " " + this.tahun;
    }
}
