/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 07 June 2019
 * Modified Date	: 07 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.service;

import com.accelerate.dash.payload.ApiResponse;
import com.accelerate.dash.payload.ErrorResponse;
import com.accelerate.dash.payload.SuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public ApiResponse sendEmail(String template) {
        Context context = new Context();
        context.setVariable("name", "Muathasim Mohamed P");
        String content;
        try {
            content = templateEngine.process("email/" + template, context);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INPUT_VALIDATION_ERROR, "Template not found.");
        }

        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject("test");
            helper.setTo("muth4muathasim@gmail.com");
            helper.setText(content, true);
        };

        try {
            javaMailSender.send(preparator);
        } catch (Exception ex) {
            return new ErrorResponse(false, StatusCodes.INTERNAL_SERVER_ERROR, "Could not send email.");
        }

        return new SuccessResponse(true, StatusCodes.SUCCESS, "Email send successfully.");
    }
}
