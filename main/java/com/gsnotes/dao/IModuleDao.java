package com.gsnotes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gsnotes.bo.Module;
import com.gsnotes.bo.Niveau;

public interface IModuleDao extends JpaRepository<Module, Long> {
	@Query("SELECT module FROM Module module where module.niveau.idNiveau = :idNiveau")
    public List<com.gsnotes.bo.Module> getModuleBy(@Param("idNiveau") Long idNiveau);
	
	public List<Module> findByNiveau(Niveau niveau);
}
