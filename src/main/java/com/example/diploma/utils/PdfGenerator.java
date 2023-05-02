package com.example.diploma.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;import java.time.LocalDate;

import com.example.diploma.enteties.Course;
import com.example.diploma.enteties.User;import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;import com.itextpdf.text.pdf.PdfWriter;

public class PdfGenerator {

  private static final String FONT_PATH = "src/main/resources/static/fonts/HeinrichScript.ttf";
  private static final String FONT_PATH_MONTSERRAT = "src/main/resources/static/fonts/Montserrat.ttf";
  private static final String BACKGROUND_IMAGE_PATH = "src/main/resources/static/images/back1.jpg";
  private static final String IMAGE_PATH = "src/main/resources/static/images/podpis.png";

  public static ByteArrayOutputStream generatePdf(Course course, User user, LocalDate date)
      throws DocumentException, IOException {
    Document document = new Document(new Rectangle(500, 300));
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PdfWriter writer = PdfWriter.getInstance(document, baos);

    Image image = Image.getInstance(IMAGE_PATH); // создаем объект Image из файла изображения
    image.scaleToFit(80, 60);
    image.setAbsolutePosition(375, 50); // устанавливаем абсолютные координаты изображения

    writer.setPageEvent(new BackgroundEvent(BACKGROUND_IMAGE_PATH));
    document.open();

    PdfContentByte canvas = writer.getDirectContent();
    canvas.addImage(image);


    BaseFont bf = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    Font font16 = new Font(bf, 16);
    Font font24 = new Font(bf, 24);
    Font font30 = new Font(bf, 30);

    BaseFont montserrat = BaseFont.createFont(FONT_PATH_MONTSERRAT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    Font montserrat30 = new Font(montserrat, 30);

    Paragraph certificateHead = new Paragraph("Cертифікат", font24);
    certificateHead.setSpacingBefore(0);
    certificateHead.setAlignment(Element.ALIGN_CENTER);
    document.add(certificateHead);

    Paragraph certificate = new Paragraph("цим підтверджується, що", font16);
    certificate.setAlignment(Element.ALIGN_CENTER);
    document.add(certificate);

    Paragraph studentName = new Paragraph(user.getFirstName().toUpperCase() + " " + user.getLastName().toUpperCase(), montserrat30);
    studentName.setAlignment(Element.ALIGN_CENTER);
    document.add(studentName);

    Paragraph status = new Paragraph("успішно завершив(ла) курс", font16);
    status.setAlignment(Element.ALIGN_CENTER);
    document.add(status);

    Paragraph certName = new Paragraph(course.getCourseTitle(), font16);
    certName.setAlignment(Element.ALIGN_CENTER);
    document.add(certName);

    canvas.beginText();
    canvas.setFontAndSize(bf, 12);
    canvas.moveText(30, 50);
    canvas.showText(date.toString());
    canvas.endText();


    canvas.beginText();
    canvas.setFontAndSize(bf, 12);
    canvas.moveText(360, 50);
    canvas.showText("Директор школи ESchool");
    canvas.endText();

    canvas.beginText();
    canvas.setFontAndSize(bf, 12);
    canvas.moveText(360, 35);
    canvas.showText("Прокопенко Максим");
    canvas.endText();


    document.close();
    return baos;
  }
}
