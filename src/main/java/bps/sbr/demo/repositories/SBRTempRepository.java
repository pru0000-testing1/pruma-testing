package bps.sbr.demo.repositories;

import bps.sbr.demo.models.SBRTemp;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface SBRTempRepository extends CrudRepository<SBRTemp, Long> {
    List<SBRTemp> findByNmperusahaan(String nmperusahaan);
    List<SBRTemp> findByNmperusahaanContainingAndIsLockedAndIdsbr(String nmperusahaan, Date isLocked, String idsbr);
    List<SBRTemp> findByPetugas(int petugas);
    List<SBRTemp> findByIsLockedIsNullAndIdsbrIsNull();

    SBRTemp findTopIdsbrIsNullAndIsLockedIsNullAndByOrderByUploadedDatetimeDesc();

}
