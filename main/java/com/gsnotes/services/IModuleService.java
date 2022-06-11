package com.gsnotes.services;

import java.util.List;

import com.gsnotes.bo.InscriptionAnnuelle;
import com.gsnotes.utils.export.ExcelExporter;

public interface IModuleService   {
 public List<com.gsnotes.bo.Module> getModuleByNiveau(Long IdNiveau);

public ExcelExporter prepareExport(List<InscriptionAnnuelle> listisc, Long long1);
}
