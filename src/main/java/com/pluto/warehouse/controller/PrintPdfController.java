package com.pluto.warehouse.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.pluto.warehouse.constant.UrlConstant;

/**
 * ログインコントロールー
 * 
 * @author pluto
 *
 */
@Controller
public class PrintPdfController {

    @GetMapping(UrlConstant.URL_PRINT_PDF)
    @ResponseBody
//	public void download(@RequestParam(name = "debtId") Long debtId, @RequestParam(name = "type") Integer type,
//			@RequestParam(name = "scene") Integer scene, HttpServletResponse response) throws IOException {
    public void download(HttpServletResponse response) throws IOException {
        OutputStream out1 = null;
        Document document = new Document();
        try {
//			TemplateVo templateVo = debtTemplateService.createPdf(debtId, scene);
//			if (Objects.isfNull(templateVo)) {
//				throw new Exception();
//			}
//			ByteArrayOutputStream baos = templateVo.getByteArrayOutputStream();
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 设置请求返回类型
//			if (2 == type) {
//				response.setHeader("Content-Disposition",
//						"attachment; filename=" + new String("testPdf".getBytes(), StandardCharsets.UTF_8));
//			}

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            PdfWriter.getInstance(document, new FileOutputStream("test.pdf".toString()));

            document.open();

            Paragraph titleParagraph = new Paragraph("bbc");
            titleParagraph.setAlignment(Element.ALIGN_CENTER);// 居中
            document.add(titleParagraph);

            document.close();

            response.setContentLength(baos.size());
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline");

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            File file = new File("test.pdf");
            FileInputStream in = new FileInputStream(file);
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            out.write(buffer);

//            FileOutputStream fos = new FileOutputStream("test.pdf".toString());
            out1 = response.getOutputStream();
            out.writeTo(out1);
            out1.flush();
            
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out1.close();
            
        }
    }

  
}
