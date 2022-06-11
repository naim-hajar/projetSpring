package com.gsnotes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gsnotes.bo.InscriptionModule;

public interface InsciptionModuleDao extends JpaRepository<InscriptionModule,Long> {

//    @Query("select module  from InscriptionModule module where module.inscriptionAnnuelle.idInscription=:is " +
//            " and module.module.idModule=:im ")
//   InscriptionModule noteOfEtdutiant(@Param("is") Long idInscrip , @Param("im") Long idModule);

	@Query("select inscModule  from InscriptionModule inscModule where inscModule.inscriptionAnnuelle.idInscription=:is " +
            " and inscModule.module.idModule=:im ")
   List<InscriptionModule> getInscModules(@Param("is") Long idInscrip , @Param("im") Long idModule);
}
