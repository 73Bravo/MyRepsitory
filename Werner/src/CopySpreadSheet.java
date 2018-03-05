import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CopySpreadSheet {

	public static void main(String[] args) {
		try {
			File excel = new File("C://Werner/Share/Sandra Report 39/Withou L/Report_39_without_L_2016.xlsx");
			FileInputStream fis;
			fis = new FileInputStream(excel);
			XSSFWorkbook book = new XSSFWorkbook(fis);
			XSSFSheet sheet = book.getSheetAt(0);
			
			String enrolledOutFile = "C://Werner/Share/Sandra Report 39/Withou L/Report_39_without_L_2016_enrolled.xlsx";
			FileOutputStream enrolledFos = new FileOutputStream(enrolledOutFile);
			XSSFWorkbook enrolledWb = new XSSFWorkbook();
			XSSFSheet enrolledSheet = enrolledWb.createSheet("ENROLLED");
			int enrolledRowNumber = 0;
			
			String plannedOutFile = "C://Werner/Share/Sandra Report 39/Withou L/Report_39_without_L_2016_planned.xlsx";
			FileOutputStream plannedFos = new FileOutputStream(plannedOutFile);
			XSSFWorkbook plannedWb = new XSSFWorkbook();
			XSSFSheet plannedSheet = plannedWb.createSheet("PLANNED");
			int plannedRowNumber = 0;

			String achievedOutFile = "C://Werner/Share/Sandra Report 39/Withou L/Report_39_without_L_2016_achieved.xlsx";
			FileOutputStream achievedFos = new FileOutputStream(achievedOutFile);
			XSSFWorkbook achievedWb = new XSSFWorkbook();
			XSSFSheet achievedSheet = achievedWb.createSheet("ACHIEVED");
			int achievedRowNumber = 0;
			
			XSSFFont enrolledFontBold= enrolledWb.createFont();
		    enrolledFontBold.setBold(true);
		    enrolledFontBold.setFontHeight(11);
			XSSFFont plannedFontBold= plannedWb.createFont();
		    enrolledFontBold.setBold(true);
		    enrolledFontBold.setFontHeight(11);
			XSSFFont achievedFontBold= achievedWb.createFont();
		    enrolledFontBold.setBold(true);
		    enrolledFontBold.setFontHeight(11);
		    
		    
		    byte[] rgb = new byte[3];
		    rgb[0] = (byte) 242; // red
		    rgb[1] = (byte) 220; // green
		    rgb[2] = (byte) 219; // blue
		    XSSFColor color = new XSSFColor(rgb);
		    
		    XSSFCellStyle enrolledStyleHeading = enrolledWb.createCellStyle();
		    short border = 1;
		    enrolledStyleHeading.setBorderTop(border); 
		    enrolledStyleHeading.setBorderBottom(border);
		    enrolledStyleHeading.setBorderLeft(border);
		    enrolledStyleHeading.setBorderRight(border);
		    enrolledStyleHeading.setFont(enrolledFontBold);
		    enrolledStyleHeading.setFillBackgroundColor(color);	
		    XSSFCellStyle plannedStyleHeading = plannedWb.createCellStyle();
		    plannedStyleHeading.setBorderTop(border); 
		    plannedStyleHeading.setBorderBottom(border);
		    plannedStyleHeading.setBorderLeft(border);
		    plannedStyleHeading.setBorderRight(border);
		    plannedStyleHeading.setFont(enrolledFontBold);
		    plannedStyleHeading.setFillBackgroundColor(color);	
		    XSSFCellStyle achievedStyleHeading = achievedWb.createCellStyle();
		    achievedStyleHeading.setBorderTop(border); 
		    achievedStyleHeading.setBorderBottom(border);
		    achievedStyleHeading.setBorderLeft(border);
		    achievedStyleHeading.setBorderRight(border);
		    achievedStyleHeading.setFont(enrolledFontBold);
		    achievedStyleHeading.setFillBackgroundColor(color);	

		    
			Iterator<Row> itr = sheet.iterator();
			while (itr.hasNext()) {
				Row inrow = itr.next();
				Iterator<Cell> cellIterator = inrow.cellIterator();
				
				if (inrow.getRowNum() == 0){
					Row enrolledRow = enrolledSheet.createRow(enrolledRowNumber);
					Row plannedRow = plannedSheet.createRow(plannedRowNumber);
					Row achievedRow = achievedSheet.createRow(achievedRowNumber);
					
					enrolledRowNumber++;
					plannedRowNumber++;
					achievedRowNumber++;
					while (cellIterator.hasNext()) {
						Cell inCell = cellIterator.next();
						Cell enrolledCell = enrolledRow.createCell(inCell.getColumnIndex());
						Cell plannedCell = plannedRow.createCell(inCell.getColumnIndex());
						Cell achievedCell = achievedRow.createCell(inCell.getColumnIndex());
						enrolledCell.setCellStyle(enrolledStyleHeading);
						plannedCell.setCellStyle(plannedStyleHeading);
						achievedCell.setCellStyle(achievedStyleHeading);
						switch (inCell.getCellType()) {
						  case Cell.CELL_TYPE_STRING:
							   enrolledCell.setCellValue(inCell.getStringCellValue());
							   plannedCell.setCellValue(inCell.getStringCellValue());
							   achievedCell.setCellValue(inCell.getStringCellValue());
							   break;
						  case Cell.CELL_TYPE_NUMERIC:
							   enrolledCell.setCellValue(inCell.getNumericCellValue());
							   plannedCell.setCellValue(inCell.getNumericCellValue());
							   achievedCell.setCellValue(inCell.getNumericCellValue());
							   break;
						  case Cell.CELL_TYPE_BOOLEAN:
							   enrolledCell.setCellValue(inCell.getBooleanCellValue());
							   plannedCell.setCellValue(inCell.getBooleanCellValue());
							   achievedCell.setCellValue(inCell.getBooleanCellValue());
							   break;
						  default:
						}
					}
					
				}
				if (inrow.getCell(5).getStringCellValue().equalsIgnoreCase("ENROLLED")) {
					Row enrolledRow = enrolledSheet.createRow(enrolledRowNumber);
					enrolledRowNumber++;
					while (cellIterator.hasNext()) {
						Cell inCell = cellIterator.next();
						Cell enrolledCell = enrolledRow.createCell(inCell.getColumnIndex());
						switch (inCell.getCellType()) {
						  case Cell.CELL_TYPE_STRING:
							   enrolledCell.setCellValue(inCell.getStringCellValue());
							   break;
						  case Cell.CELL_TYPE_NUMERIC:
							   enrolledCell.setCellValue(inCell.getNumericCellValue());
							   break;
						  case Cell.CELL_TYPE_BOOLEAN:
							   enrolledCell.setCellValue(inCell.getBooleanCellValue());
							   break;
						  default:
						}
					}
				}

				if (inrow.getCell(5).getStringCellValue().equalsIgnoreCase("PLANNED")) {
					Row plannedRow = plannedSheet.createRow(plannedRowNumber);
					plannedRowNumber++;
					while (cellIterator.hasNext()) {
						Cell inCell = cellIterator.next();
						Cell plannedCell = plannedRow.createCell(inCell.getColumnIndex());
						switch (inCell.getCellType()) {
						  case Cell.CELL_TYPE_STRING:
							   plannedCell.setCellValue(inCell.getStringCellValue());
							   break;
						  case Cell.CELL_TYPE_NUMERIC:
							   plannedCell.setCellValue(inCell.getNumericCellValue());
							   break;
						  case Cell.CELL_TYPE_BOOLEAN:
							   plannedCell.setCellValue(inCell.getBooleanCellValue());
							   break;
						  default:
						}
					}
				}

				if (inrow.getCell(5).getStringCellValue().equalsIgnoreCase("ACHIEVED")) {
					Row achievedRow = achievedSheet.createRow(achievedRowNumber);
					achievedRowNumber++;
					while (cellIterator.hasNext()) {
						Cell inCell = cellIterator.next();
						Cell achievedCell = achievedRow.createCell(inCell.getColumnIndex());
						switch (inCell.getCellType()) {
						  case Cell.CELL_TYPE_STRING:
							   achievedCell.setCellValue(inCell.getStringCellValue());
							   break;
						  case Cell.CELL_TYPE_NUMERIC:
							   achievedCell.setCellValue(inCell.getNumericCellValue());
							   break;
						  case Cell.CELL_TYPE_BOOLEAN:
							   achievedCell.setCellValue(inCell.getBooleanCellValue());
							   break;
						  default:
						}
					}
				}
				
				
			}
			enrolledWb.write(enrolledFos);
			enrolledWb.close();
			enrolledFos.flush();
			enrolledFos.close();
			plannedWb.write(plannedFos);
			plannedWb.close();
			plannedFos.flush();
			plannedFos.close();
			achievedWb.write(achievedFos);
			achievedWb.close();
			achievedFos.flush();
			achievedFos.close();
			book.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
