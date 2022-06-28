package com.wisdge.pdf;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.properties.TextAlignment;
import lombok.extern.slf4j.Slf4j;

/**
 * 监听事件 添加所属人
 */
@Slf4j
class OwnerMark implements IEventHandler {
    private String txtMark;
    private int txtSize;
    private boolean topLevel;

    public OwnerMark(String txtMark, int txtSize, boolean topLevel) {
        this.txtMark = txtMark;
        this.txtSize = txtSize;
        this.topLevel = topLevel;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDocument = documentEvent.getDocument();
        PdfPage pdfPage = documentEvent.getPage();
        try {
            PdfFont pdfFont = PdfFontFactory.createFont(PdfUtils.ITEXT_CHFONT_NAME, PdfUtils.ITEXT_CHFONT_CODE);
            if (topLevel) {
                PdfCanvas pdfCanvas = new PdfCanvas(pdfPage.newContentStreamAfter(), pdfPage.getResources(), pdfDocument);
                Canvas canvas = new Canvas(pdfCanvas.stroke(), pdfPage.getPageSize());
                canvas.setFont(pdfFont);
                canvas.setFontSize(txtSize);
                canvas.setFontColor(ColorConstants.LIGHT_GRAY, 0.5f);
                canvas.showTextAligned(txtMark, 10, 10, TextAlignment.LEFT);
                canvas.close();
                pdfCanvas.release();
            } else {
                PdfCanvas pdfCanvas = new PdfCanvas(pdfPage.newContentStreamBefore(), pdfPage.getResources(), pdfDocument);
                Canvas canvas = new Canvas(pdfCanvas, pdfPage.getPageSize());
                canvas.setFont(pdfFont);
                canvas.setFontSize(txtSize);
                canvas.setFontColor(new DeviceRgb(168, 168, 168));
                canvas.showTextAligned(txtMark, 10, 10, TextAlignment.LEFT);
                canvas.close();
                pdfCanvas.release();
            }
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}

