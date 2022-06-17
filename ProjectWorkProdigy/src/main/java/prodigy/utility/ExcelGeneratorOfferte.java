package prodigy.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import prodigy.model.OfferteModel;

public class ExcelGeneratorOfferte {

    public static ByteArrayInputStream offerteExcel(List<OfferteModel> offerte) throws IOException {
	String[] COLUMNs = { "idOfferta", "descrizione", "prezzo", "dataAttivazione", "idCliente" };

	try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

	    Sheet sheet = workbook.createSheet("Offerta");

	    Font headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    headerFont.setColor(IndexedColors.RED.getIndex());

	    CellStyle headerCellStyle = workbook.createCellStyle();
	    headerCellStyle.setFont(headerFont);

	    // Row for Header
	    Row headerRow = sheet.createRow(0);

	    // Header

	    for (int col = 0; col < COLUMNs.length; col++) {
		Cell cell = headerRow.createCell(col);
		cell.setCellValue(COLUMNs[col]);
		cell.setCellStyle(headerCellStyle);
	    }

	    int rowIdx = 1;

	    for (OfferteModel offerta : offerte) {
		Row row = sheet.createRow(rowIdx++);

		row.createCell(0).setCellValue(offerta.getIdOfferta());
		row.createCell(1).setCellValue(offerta.getDescrizione());
		row.createCell(2).setCellValue(offerta.getPrezzo());
		row.createCell(3).setCellValue(offerta.getDataAttivazione().toString());
		row.createCell(4).setCellValue(offerta.getIdCliente());

	    }

	    workbook.write(out);
	    return new ByteArrayInputStream(out.toByteArray());
	}
    }
}
