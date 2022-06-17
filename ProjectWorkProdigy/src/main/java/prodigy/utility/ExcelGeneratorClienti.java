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

import prodigy.model.ClientiModel;

public class ExcelGeneratorClienti {

    public static ByteArrayInputStream clientiExcel(List<ClientiModel> clienti) throws IOException {
	String[] COLUMNs = { "idCliente", "nome", "cognome", "email", "telefono" };

	try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

	    Sheet sheet = workbook.createSheet("Cliente");

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
	    for (ClientiModel cliente : clienti) {
		Row row = sheet.createRow(rowIdx++);

		row.createCell(0).setCellValue(cliente.getIdCliente());
		row.createCell(1).setCellValue(cliente.getNome());
		row.createCell(2).setCellValue(cliente.getCognome());
		row.createCell(3).setCellValue(cliente.getEmail());
		row.createCell(4).setCellValue(cliente.getTelefono());
	    }

	    workbook.write(out);
	    return new ByteArrayInputStream(out.toByteArray());
	}
    }
}
