package com.example.dash.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Component
public class PdfGenerationUtility {

	@Autowired
	private TemplateEngine templateEngine;
	
	public byte[] createPdf(String templateName, Map map) {
		Context context = new Context();
		if (map != null) {
			Iterator itMap = map.entrySet().iterator();
			while (itMap.hasNext()) {
				Map.Entry entry = (Map.Entry) itMap.next();
				context.setVariable(entry.getKey().toString(), entry.getValue());
			}
		}
		
		String processedHtml = templateEngine.process(templateName, context);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		try {
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml);
			renderer.layout();
			renderer.createPDF(outputStream);
			renderer.finishPDF();
		} catch (Exception ex) {
			return null;
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException ex) {
					return null;
				}
			}
		}
		
		return outputStream.toByteArray();
	}
}
