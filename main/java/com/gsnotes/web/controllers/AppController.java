package com.gsnotes.web.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gsnotes.bo.Element;
import com.gsnotes.bo.InscriptionAnnuelle;
//import com.gsnotes.bo.Element;
import com.gsnotes.bo.Niveau;
import com.gsnotes.services.IElementService;
//import com.gsnotes.services.IElementService;
import com.gsnotes.services.IModuleService;
import com.gsnotes.services.INiveauService;
import com.gsnotes.services.InscriptionAnnuelService;
import com.gsnotes.utils.export.ExcelExporter;
import com.gsnotes.web.models.NiveauModel;

@Controller
@RequestMapping("/AppExport/test") 

public class AppController {
	@Autowired
	private INiveauService niveauService;
	@Autowired
	private IModuleService moduleService;
	@Autowired
	private IElementService elementService;
    @Autowired
    private InscriptionAnnuelService inscription;
	

	public String   exportToExcel( @ModelAttribute("NiveauModel")  NiveauModel nivmodel,HttpServletResponse response) throws IOException {
		Long id= nivmodel.getIdNiveau();
		Niveau niv= niveauService.getNiveauById(Long.valueOf(id));
		System.out.println(niv.getTitre());
		Long nv=niv.getIdNiveau();
		List<com.gsnotes.bo.Module> list= moduleService.getModuleByNiveau(nv);

		for(com.gsnotes.bo.Module m : list) {
			System.out.println(m.getTitre());
			List<Element> list2= elementService.getElementByModule(m.getIdModule());
			for(Element e: list2) {
				System.out.println(e.getNom());
			}


		}
		
		
         List <InscriptionAnnuelle> listisc= inscription.getInscriptionByNiveau(nv);
         
        	 
        		response.setContentType("application/octet-stream");
        		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        		String currentDateTime = dateFormatter.format(new Date());

        		String headerKey = "Content-Disposition";
        		String headerValue = "attachment; filename=Delibiration_" + currentDateTime + ".xlsx";
        		response.setHeader(headerKey, headerValue);

        		
         ExcelExporter excelExporter = moduleService.prepareExport(listisc,nv);

     	excelExporter.export(response);
		


		return headerValue;
	}
	
	}
