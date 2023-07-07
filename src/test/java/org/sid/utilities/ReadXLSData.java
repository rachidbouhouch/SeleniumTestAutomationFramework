package org.sid.utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadXLSData {

    public static void main(String[] args) throws IOException {
        ReadXLSData.getData();
    }

    public static Object[][] getData() throws IOException {
        ReadConfigProperties readConfigProperties=new ReadConfigProperties("config");

        File f = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\"+readConfigProperties.getPropertiesConfig().getProperty("fileExcelName"));
        FileInputStream fis=new FileInputStream(f);
        Workbook workbook= WorkbookFactory.create(fis);
        Sheet sheetName=workbook.getSheet(readConfigProperties.getPropertiesConfig().getProperty("sheetName"));

        int totalRows=sheetName.getLastRowNum(); // start from 0
        //getPhysicalNumberOfRows() // start from 1

        //System.out.println(totalRows);
        Row rowCells=sheetName.getRow(0);
        int totalCols=rowCells.getLastCellNum();
        //System.out.println(totalCols);
        String[][] testData=new String[totalRows][totalCols];

        DataFormatter format=new DataFormatter();
        for(int i=1;i<=totalRows;i++){
            for(int j=0;j<totalCols;j++){
                testData[i-1][j]=format.formatCellValue(sheetName.getRow(i).getCell(j));
                System.out.println(testData[i-1][j]);
            }
        }

        return testData;

    }
}
