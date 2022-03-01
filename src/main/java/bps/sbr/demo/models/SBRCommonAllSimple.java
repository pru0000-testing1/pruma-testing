package bps.sbr.demo.models;

import com.opencsv.bean.CsvBindByName;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sbr_common_all_simple")
@Indexed
public class SBRCommonAllSimple{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CsvBindByName(column = "idsbr")
    @Column(name="\"idsbr\"", columnDefinition = "nvarchar")
    private String idsbr;
    @CsvBindByName(column = "nmperusahaan")
    @Column(name="\"nmperusahaan\"", columnDefinition = "nvarchar")
    private String nmperusahaan;
    @CsvBindByName(column = "nmkomersial")
    @Column(columnDefinition = "nvarchar")
    private String nmkomersial;
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
    @CsvBindByName(column = "kodearea")
    @Column(columnDefinition = "nvarchar")
    private String kodearea;
    @CsvBindByName(column = "notelp")
    @Column(columnDefinition = "nvarchar")
    private String notelp;
    @CsvBindByName(column = "kegutama")
    @Column(columnDefinition = "nvarchar")
    private String kegutama;
    @CsvBindByName(column = "kdkbli")
    @Column(columnDefinition = "nvarchar")
    private String kdkbli;
    @CsvBindByName(column = "end")
    @Column(name="\"end\"", columnDefinition = "nvarchar")
    private String end;
    @CsvBindByName(column = "nofax")
    @Column(columnDefinition = "nvarchar")
    private String nofax;
    @CsvBindByName(column = "kodepos")
    @Column(columnDefinition = "nvarchar")
    private String kodepos;
    @CsvBindByName(column = "totaltk")
    private int totaltk;
    @CsvBindByName(column = "skalausaha")
    @Column(columnDefinition = "nvarchar")
    private String skalausaha;
    @CsvBindByName(column = "email")
    @Column(columnDefinition = "nvarchar")
    private String email;
    @CsvBindByName(column = "flagbu")
    @Column(columnDefinition = "nvarchar")
    private String flaglbu;
    @CsvBindByName(column = "tahunberdiri")
    @Column(columnDefinition = "nvarchar")
    private String tahunberdiri;
    @CsvBindByName(column = "tahunoperasi")
    @Column(columnDefinition = "nvarchar")
    private String tahunoperasi;
    @CsvBindByName(column = "latitude")
    @Column(columnDefinition = "nvarchar")
    private String latitude;
    @CsvBindByName(column = "longitude")
    @Column(columnDefinition = "nvarchar")
    private String longitude;
    private int uploaded_petugas;
    private Date uploaded_datetime;
    @Column(columnDefinition = "nvarchar")
    private String uploaded_filename;

    public int getUploaded_petugas() {
        return uploaded_petugas;
    }

    public void setUploaded_petugas(int uploaded_petugas) {
        this.uploaded_petugas = uploaded_petugas;
    }

    public Date getUploaded_datetime() {
        return uploaded_datetime;
    }

    public void setUploaded_datetime(Date uploaded_datetime) {
        this.uploaded_datetime = uploaded_datetime;
    }

    public String getUploaded_filename() {
        return uploaded_filename;
    }

    public void setUploaded_filename(String uploaded_filename) {
        this.uploaded_filename = uploaded_filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdsbr() {
        return idsbr;
    }

    public void setIdsbr(String idsbr) {
        this.idsbr = idsbr;
    }

    public String getNmperusahaan() {
        return nmperusahaan;
    }

    public void setNmperusahaan(String nmperusahaan) {
        this.nmperusahaan = nmperusahaan;
    }

    public String getNmkomersial() {
        return nmkomersial;
    }

    public void setNmkomersial(String nmkomersial) {
        this.nmkomersial = nmkomersial;
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

    public String getKegutama() {
        return kegutama;
    }

    public void setKegutama(String kegutama) {
        this.kegutama = kegutama;
    }

    public String getKdkbli() {
        return kdkbli;
    }

    public void setKdkbli(String kdkbli) {
        this.kdkbli = kdkbli;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getNofax() {
        return nofax;
    }

    public void setNofax(String nofax) {
        this.nofax = nofax;
    }

    public String getKodepos() {
        return kodepos;
    }

    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
    }

    public int getTotaltk() {
        return totaltk;
    }

    public void setTotaltk(int totaltk) {
        this.totaltk = totaltk;
    }

    public String getSkalausaha() {
        return skalausaha;
    }

    public void setSkalausaha(String skalausaha) {
        this.skalausaha = skalausaha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFlaglbu() {
        return flaglbu;
    }

    public void setFlaglbu(String flaglbu) {
        this.flaglbu = flaglbu;
    }

    public String getTahunberdiri() {
        return tahunberdiri;
    }

    public void setTahunberdiri(String tahunberdiri) {
        this.tahunberdiri = tahunberdiri;
    }

    public String getTahunoperasi() {
        return tahunoperasi;
    }

    public void setTahunoperasi(String tahunoperasi) {
        this.tahunoperasi = tahunoperasi;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
