package com.gsnotes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gsnotes.bo.InscriptionMatiere;

public interface InscriptionElementsDao extends JpaRepository<InscriptionMatiere,Long> {
    @Query("select elt  from InscriptionMatiere elt where elt.inscriptionAnnuelle.idInscription=:ins " +
            " and  elt.matiere.idMatiere=:im")
    InscriptionMatiere notesElementsOfEtd(@Param("ins") Long idInscrip , @Param("im") Long idElment);
}