package com.gsnotes.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.IModuleDao;
import com.gsnotes.dao.INiveauDao;
import com.gsnotes.services.INiveauService;
@Service
@Transactional
public class NiveauServiceImpl implements INiveauService{
 @Autowired
 private INiveauDao niveauDao;
 @Autowired
 private IModuleDao moduleDao;

@Override
public Niveau getNiveauById(Long IdNiveau) {

	
	return niveauDao.getById(IdNiveau);
	
}
}
