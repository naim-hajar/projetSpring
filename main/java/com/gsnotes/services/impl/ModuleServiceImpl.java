package com.gsnotes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.bo.InscriptionModule;
import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.IModuleDao;
import com.gsnotes.dao.INiveauDao;
import com.gsnotes.dao.InsciptionModuleDao;
import com.gsnotes.services.IModuleService;
import com.gsnotes.utils.export.ExcelExporter;
@Service
@Transactional
public class ModuleServiceImpl implements IModuleService {

	@Autowired
	private IModuleDao moduleDao;
	 @Autowired
	 private INiveauDao niveauDao;
	 @Autowired
		private InsciptionModuleDao inscm;
	
	@Override
	public List<com.gsnotes.bo.Module> getModuleByNiveau(Long IdNiveau) {
		// TODO Auto-generated method stub
		return moduleDao.getModuleBy(IdNiveau);
	}

public ExcelExporter prepareExport(List<InscriptionAnnuelle> list,Long idNiveau) {
		
		Niveau niveau = niveauDao.getById(idNiveau);
		  //Object moduleService = null;
		  List<com.gsnotes.bo.Module> listmd= getModuleByNiveau(niveau.getIdNiveau());
		// header
		  System.out.println(listmd);
		
		    String[] columnNames = new String[50];
		    columnNames[0]="ID ETUDIANT";
		    columnNames[1]="CNE";
		    columnNames[2]="NOM";
		    columnNames[3]="PRENOM";
		   
		    int i = 4;
	        for (com.gsnotes.bo.Module md: listmd) {
	        	
	        	columnNames[i++] = md.getTitre();
	        	columnNames[i++] = "Moyenne";
	        	columnNames[i++] = "Validation";
	        	
	        }
	        
	        
	        columnNames[i++]="Moyenne Annee";
            columnNames[i++]="Rang";
		           
	        
			        String[][] data = new String[list.size()][columnNames.length];

			        int i1 = 0;
			        double moyGen = 0.0;
			        for (InscriptionAnnuelle ia : list) {
                        Long idInscA = ia.getIdInscription();
			            data[i1][0] = String.valueOf(ia.getEtudiant().getIdUtilisateur());
			            data[i1][1] = ia.getEtudiant().getCne();
			            data[i1][2] = ia.getEtudiant().getNom();
			            data[i1][3] = ia.getEtudiant().getPrenom();
			            
			            int j = 4;
			            for(InscriptionModule im:inscm.getInscModules(idInscA, idNiveau)) {
                            
			            	data[i1][j++] =im.getModule().getCode() ;
				        	double noteSR = im.getNoteSR();
				        	double noteSN = im.getNoteSN();
				        	 moyGen = 0.0;
				        	
				        	double noteFinal = 0.0;
				        	if(noteSR!= 0.0) {
				        		noteFinal = (noteSR > noteSN?noteSR:noteSN);
				        		
				        	}
				        	else  {
				        		noteFinal = noteSN;
				        	}
				        
				           
				            data[i1][j++] = Double.toString(noteFinal);
				            data[i1][j++] = ( noteFinal>=12 ?"V":"NV");
				        	
						data[i1][j++] =  Double.toString(moyGen/inscm.getInscModules(idInscA, idNiveau).size());
						i1++;

	        	}
	        
	}
	return new ExcelExporter(columnNames, data, "noteDeliberation");

	}


}
	
	

