package com.wisdge.pdf;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import lombok.extern.slf4j.Slf4j;

/**
 * 监听事件 添加水印
 */
@Slf4j
class WaterMark implements IEventHandler {
    private String txtMark;
    private int txtSize;
    private boolean topLevel;

    public WaterMark(String txtMark, int txtSize, boolean topLevel) {
        this.txtMark = txtMark;
        this.txtSize = txtSize;
        this.topLevel = topLevel;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
        PdfDocument document = documentEvent.getDocument();
        PdfPage pdfPage = documentEvent.getPage();
        PageSize pageSize = document.getDefaultPageSize();
        try {
            PdfFont pdfFont = PdfFontFactory.createFont(PdfUtils.ITEXT_CHFONT_NAME, PdfUtils.ITEXT_CHFONT_CODE);
            PdfCanvas pdfCanvas = new PdfCanvas(topLevel ? pdfPage.newContentStreamAfter() : pdfPage.newContentStreamBefore(), pdfPage.getResources(), document);
            Paragraph paragraph = new Paragraph(txtMark);
            paragraph.setRotationAngle(170);
            Canvas canvas = new Canvas(pdfCanvas, pdfPage.getPageSize());
            canvas.setFont(pdfFont);
            canvas.setFontSize(txtSize);
            canvas.setFontColor(ColorConstants.LIGHT_GRAY, 0.3f);
            canvas.showTextAligned(paragraph, pageSize.getWidth()/2, pageSize.getHeight()/2, TextAlignment.CENTER, VerticalAlignment.MIDDLE);
            canvas.close();
            pdfCanvas.release();
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
