/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: Change file path to the location of the template and accompanying files
 */


package com.accelerate.dash.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

// Utility for generating PDF for academic report
@Component
public class PdfGenerationUtility {

	private static final String FILE_PATH = "file:///D:/Work/Dash/dashServices/src/main/resources/static/";

	@Autowired
	private TemplateEngine templateEngine;
	
	public byte[] createPdf(String dirName, String templateName, Map<String, Object> map) {
		// Process the template
		Context context = new Context();
		if (map != null) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				context.setVariable(entry.getKey().toString(), entry.getValue());
			}
		}
		
		String processedHtml = templateEngine.process(dirName + "/" + templateName, context);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		// Generate the pdf into a byte array
		try {
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(processedHtml, FILE_PATH + dirName + "/");
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
