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

import prodigy.model.PreventiviModel;

public class PdfGeneratorPreventivi {

    private static final Logger logger = LoggerFactory.getLogger(PdfGeneratorPreventivi.class);

    public static ByteArrayInputStream pdfPreventivi(List<PreventiviModel> preventivi) {

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

	    PdfPTable table = new PdfPTable(4);
	    table.setWidthPercentage(100);
	    table.setWidths(new int[] { 2, 10, 3, 3 });

	    PdfPCell hcell;
	    hcell = new PdfPCell(new Phrase("Id", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("descrizione", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("prezzo", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("id cliente", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    for (PreventiviModel preventivo : preventivi) {

		PdfPCell cell;

		cell = new PdfPCell(new Phrase(preventivo.getIdPreventivo().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(preventivo.getDescrizione()));
		cell.setPaddingLeft(5);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(preventivo.getPrezzo().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingRight(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(preventivo.getIdCliente().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingRight(5);
		table.addCell(cell);
	    }

	    Chunk chunk = new Chunk("Lista Preventivi", titleFont);

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
