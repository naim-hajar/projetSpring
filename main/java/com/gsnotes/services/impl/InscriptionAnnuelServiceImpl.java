package com.gsnotes.services.impl;

import java.util.List;

import javax.transaction.Transactional;
import com.gsnotes.dao.InscriptionAnnuelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.services.InscriptionAnnuelService;

@Service
@Transactional
public class InscriptionAnnuelServiceImpl implements InscriptionAnnuelService{
	
  @Autowired
  private InscriptionAnnuelDao InscriptionAnnuelDao ;
  

	@Override
	public List<InscriptionAnnuelle> getInscriptionByNiveau(Long id) {
		return  (List<InscriptionAnnuelle>) InscriptionAnnuelDao.getById(id);
	}

	

}