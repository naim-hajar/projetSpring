package com.gsnotes.dao;
import com.gsnotes.bo.InscriptionAnnuelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface InscriptionAnnuelDao extends JpaRepository<InscriptionAnnuelle,Long> {

    @Query("select ia from InscriptionAnnuelle ia where ia.niveau.idNiveau=:n")
    List<InscriptionAnnuelle> etdInOrd(@Param("n") Long niv);

}