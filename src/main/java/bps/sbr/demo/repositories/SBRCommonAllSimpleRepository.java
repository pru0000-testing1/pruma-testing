package bps.sbr.demo.repositories;

import bps.sbr.demo.models.SBRCommonAllSimple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SBRCommonAllSimpleRepository extends  CrudRepository<SBRCommonAllSimple, Long> {
        List<SBRCommonAllSimple> findByNmperusahaan(String nmperusahaan);

        @Query(value = "DECLARE\n" +
                "    @nama nvarchar(255),   \n" +
                "    @alamat nvarchar(255),\n" +
                "\t@provinsi char(2)\n" +
                "\n" +
                "SET @nama= :input_nmperusahaan \n" +
                "SET\t@alamat= :input_alamat \n" +
                "SET @provinsi= :input_kdprop \n" +
                "  \n" +
                "\tselect top 25 *\n" +
                "\tfrom\n" +
                "\t(\n" +
                "\tSELECT \n" +
                "\n" +
                " LTRIM(RTRIM(idsbr)) as idsbr,LTRIM(RTRIM(nmperusahaan)) as nmperusahaan,LTRIM(RTRIM(nmkomersial)) as nmkomersial,LTRIM(RTRIM(alamat)) as alamat, LTRIM(RTRIM(kddesa)) as kddesa, LTRIM(RTRIM(kdkec)) as kdkec, LTRIM(RTRIM(kdkab)) as kdkab, LTRIM(RTRIM(kdprop)) as kdprop, \n" +
                " kodearea, notelp, kegutama, kdkbli,\n" +
                "    ((2.0*(CASE WHEN KEY_TBL.RANK IS NULL then 0 else KEY_TBL.RANK end))+\n" +
                "(1.9*(CASE WHEN KEY_TBL2.RANK IS NULL then 0 else KEY_TBL2.RANK end))) as c FROM sbr_common_all_simple AS FT_TBL \n" +
                "\tFULL OUTER JOIN\n" +
                "\t\tFREETEXTTABLE(sbr_common_all_simple , nmperusahaan, @nama) AS KEY_TBL ON FT_TBL.idsbr = KEY_TBL.[KEY]\n" +
                "\t\t\tFULL OUTER JOIN\n" +
                "\t\t\t\tFREETEXTTABLE (sbr_common_all_simple, alamat, @alamat) \n" +
                "\t\t\t\tAS KEY_TBL2\n" +
                "\t\t\t\tON FT_TBL.idsbr = KEY_TBL2.[KEY]\n" +
                "\t\t\t\twhere kdprop=@provinsi and [end] is null\n" +
                "\t\t\t) as T\n" +
                "order by T.c desc", nativeQuery = true)
        List<Map<String, Object>> freeTextNmperusahaanAndAlamatAndKdprop(@Param("input_nmperusahaan") String nmperusahaan, @Param("input_alamat") String alamat, @Param("input_kdprop") String kdprop);
}
