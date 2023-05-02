package com.example.diploma.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;

public class BackgroundEvent extends PdfPageEventHelper {
  private Image backgroundImage;

  public BackgroundEvent(String imagePath) throws IOException, BadElementException {
    this.backgroundImage = Image.getInstance(imagePath);
  }

  @Override
  public void onEndPage(PdfWriter writer, Document document) {
    PdfContentByte canvas = writer.getDirectContentUnder();
    Rectangle rect = document.getPageSize();
    try {
      canvas.addImage(backgroundImage, rect.getWidth(), 0, 0, rect.getHeight(), 0, 0);
    } catch (DocumentException e) {
      throw new RuntimeException(e);
    }
  }
}
