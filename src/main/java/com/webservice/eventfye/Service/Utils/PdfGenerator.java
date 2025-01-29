package com.webservice.eventfye.Service.Utils;

import com.webservice.eventfye.Model.Certificado;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class PdfGenerator {

    public void gerarCertificadoPdf(Certificado certificado) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 750);

        contentStream.showText("CERTIFICADO DE PARTICIPAÇÃO");
        contentStream.endText();

        contentStream.setFont(PDType1Font.HELVETICA_OBLIQUE, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 700);

        contentStream.showText("Este é para certificar que");
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("O(a) participante " + certificado.getNomeUsuario().getNomeUsuario());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Participou do evento " + certificado.getNomeEvento().getNomeEvento());
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Durante o período de " + certificado.getNomeEvento().getDataInicioEvento().toLocalDate() + " a " + certificado.getNomeEvento().getDataFimEvento().toLocalDate());
        contentStream.newLineAtOffset(0, -40);
        contentStream.showText("Emitido em: " + certificado.getDataEmissaoCertificado().toString());

        contentStream.endText();

        document.save(certificado.getCaminhoArquivoCertificado());
        document.close();
    }
}