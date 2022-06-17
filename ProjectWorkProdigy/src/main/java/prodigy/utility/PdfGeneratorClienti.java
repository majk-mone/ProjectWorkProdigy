package prodigy.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import prodigy.model.ClientiModel;

public class PdfGeneratorClienti {

    private static final Logger logger = LoggerFactory.getLogger(PdfGeneratorClienti.class);

    public static ByteArrayInputStream pdfClienti(List<ClientiModel> cliente) {

	Document document = new Document();
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	try {
	    PdfWriter.getInstance(document, out);
	    document.open();

	    Font titleFont = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC);
	    titleFont.setColor(255, 0, 0);
	    titleFont.isBold();
	    titleFont.setSize(25);

	    Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

	    PdfPTable table = new PdfPTable(5);
	    table.setWidthPercentage(100);
	    table.setWidths(new int[] { 2, 3, 3, 10, 5 });

	    PdfPCell hcell;
	    hcell = new PdfPCell(new Phrase("Id", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("nome", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("cognome", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("email", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("telefono", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    for (ClientiModel clienti : cliente) {

		PdfPCell cell;
		cell = new PdfPCell(new Phrase(clienti.getIdCliente().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(clienti.getNome()));
		cell.setPaddingLeft(5);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(clienti.getCognome().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingRight(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(clienti.getEmail().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingRight(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(clienti.getTelefono().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingRight(5);
		table.addCell(cell);

	    }
	    Chunk chunk = new Chunk("Lista Clienti", titleFont);

	    chunk.setBackground(BaseColor.YELLOW);
	    com.itextpdf.text.Paragraph titolo3 = new com.itextpdf.text.Paragraph(chunk);
	    titolo3.setAlignment(Element.ALIGN_CENTER);
	    document.add(titolo3);

	    document.add(Chunk.NEWLINE);

	    document.add(table);
	    document.close();

	} catch (DocumentException ex) {
	    logger.error("Error occurred: {0}", ex);
	}

	return new ByteArrayInputStream(out.toByteArray());
    }
}
