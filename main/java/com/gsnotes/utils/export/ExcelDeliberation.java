package com.gsnotes.utils.export;




import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExcelDeliberation {
	private XSSFWorkbook workbook;

    private XSSFSheet sheet;
    private List<Row> rows=new ArrayList<>();
    private List<List<CellAddress>> cellNotesAdd=new ArrayList<>();
    private List<CellAddress> AvgAddresse=new ArrayList<>();
    private int ColomNbre;

    public ExcelDeliberation(String sheet){

        workbook=new XSSFWorkbook();
        this.sheet=workbook.createSheet(sheet);
    }

    public void insertInFile( int colIndex , List<List<Object>> Data) {
        int j=0;
        for (List<Object> datatype : Data) {
           Row row=rows.get(j++);
            int colNum = colIndex;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                } else if (field instanceof Double) {
                    cell.setCellValue((Double) field);
                } else if (field instanceof Long) {
                    cell.setCellValue((Long) field);
                }
            }
        }
    }




    private CellAddress createCell(Row row, int columnCount, Object value) {

        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue((String) value);
        }
        return cell.getAddress();
    }

    public void Rows(int start,int size){
        this.ColomNbre=size;
        for (int i=0;i<size;i++){
            Row row=sheet.createRow(start++);
            rows.add(row);
        }

    }

    public List<Row> getRows() {
        return rows;
    }

    public void CellwithFormule( int colIndex , List<Object> pData) {
        int i=0;
        List<CellAddress> addresses=new ArrayList<>();
        for (Object data : pData) {
            Row row = rows.get(i++);
            CellAddress addr=createCell(row,colIndex,data);
            Cell cell=row.createCell(colIndex+1);
            cell.setCellFormula("IF(AND("+addr+">=12,ISNUMBER("+addr+")),\"V\",\"NV\")");

            addresses.add(addr);
        }
        cellNotesAdd.add(addresses);
    }

    public void ColoumMoyenneGeneral(int col){

        for (int i=0;i<ColomNbre;i++) {
            String formul="AVERAGE(";
            for (int k=0;k<cellNotesAdd.size();k++) {
                List<CellAddress> cellAddresses=cellNotesAdd.get(k);
                if(k==cellNotesAdd.size()-1){
                    formul+=cellAddresses.get(i)+")";
                }else {
                    formul+=cellAddresses.get(i)+",";
                }
            }

            Row row= rows.get(i);
            Cell cell=row.createCell(col);
            cell.setCellFormula(formul);
            AvgAddresse.add(cell.getAddress());
        }
    }

    public void RankColoum(int col){
        int i=0;
        for (CellAddress add:AvgAddresse){
            Row row=rows.get(i++);
            Cell cell=row.createCell(col);
            String formul="RANK.EQ("+add+","+AvgAddresse.get(0)+":"+AvgAddresse.get(AvgAddresse.size()-1)+")";
            cell.setCellFormula(formul);
        }
    }


    public void exportInbureau() throws IOException {

        FileOutputStream out = new FileOutputStream("C:/Users/pc/Desktop/GI2/notesDel.xlsx");
        workbook.write(out);
        out.close();
    }


}
