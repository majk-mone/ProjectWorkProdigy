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

import prodigy.model.PreventiviModel;

public class ExcelGeneratorPreventivi {

    public static ByteArrayInputStream preventiviExcel(List<PreventiviModel> preventivi) throws IOException {

	String[] COLUMNs = { "idPreventivo", "descrizione", "prezzo", "idCliente" };

	try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

	    Sheet sheet = workbook.createSheet("Preventivo");

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

	    for (PreventiviModel preventivo : preventivi) {
		Row row = sheet.createRow(rowIdx++);

		row.createCell(0).setCellValue(preventivo.getIdPreventivo());
		row.createCell(1).setCellValue(preventivo.getDescrizione());
		row.createCell(2).setCellValue(preventivo.getPrezzo());
		row.createCell(3).setCellValue(preventivo.getIdCliente());
	    }

	    workbook.write(out);
	    return new ByteArrayInputStream(out.toByteArray());
	}
    }
}
