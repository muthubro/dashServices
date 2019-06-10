/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 08 June 2019
 * Modified Date	: 09 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.controller;

import javax.servlet.http.HttpServletResponse;

import com.accelerate.dash.payload.LoginWithDashRequest;
import com.accelerate.dash.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/login-with-dash")
public class LoginWithDashController {

    @Autowired
    private AuthService authService;

    @GetMapping("")
    public String preLogin(@RequestParam("redirectUrl") String redirectUrl, 
                           @RequestParam(value = "protocol", defaultValue = "https") String protocol,
                           @RequestParam(value = "reference", required = false) String reference,
                           Model model) {
        model.addAttribute("url", redirectUrl);
        model.addAttribute("protocol", protocol);
        model.addAttribute("request", new LoginWithDashRequest());
        return "loginWithDash/login";
    }

    @PostMapping("")
    public String loginWithDash(@ModelAttribute LoginWithDashRequest request, 
                              HttpServletResponse response,
                              RedirectAttributes redirectAttributes) {
        String jwt = authService.loginWithDash(request);

        if (jwt != null)
            redirectAttributes.addAttribute("token", jwt);

        String url = request.getProtocol() + "://" + request.getRedirectUrl();
        return "redirect:" + url;
    }
}
