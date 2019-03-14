/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;


/**
 *
 * @author Binh
 */
@Component
public class PdfGenaratorUtil {

    @Autowired
    private TemplateEngine templateEngine;

    public String createPdf(String templateName, Map<Object, Object> map)
            throws Exception {
        String finalFileName;
        Assert.notNull(templateName, "The templateName can not be null");
        Context ctx = new Context();
        if (map != null) {
            Iterator<Entry<Object, Object>> itMap = map.entrySet().iterator();
            while (itMap.hasNext()) {
                Map.Entry<Object, Object> pair = itMap.next();
                ctx.setVariable(pair.getKey().toString(), pair.getValue());
            }
        }
        String processedHtml = templateEngine.process(templateName, ctx);
        FileOutputStream os = null;
        String fileName = "tickets";
        try {
            final File outputFile = File.createTempFile(fileName, ".pdf",
                    new File("C:\\Users\\Binh\\Documents\\NezunkMintAMoziban\\MovieSpringProject\\src\\main\\resources\\static\\tickets"));
            os = new FileOutputStream(outputFile);
            
            
            
            
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();
            finalFileName = outputFile.getName();
            System.out.println("PDF created successfully");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    /* ignore */
                }
            }
        }
        
        
        return finalFileName;
    }
}
