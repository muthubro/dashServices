/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 08 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

public class LoginWithDashRequest {

    private String username;

    private String password;

    private String redirectUrl;

    private String protocol;

    public LoginWithDashRequest() {}

	public LoginWithDashRequest(String username, String password, String redirectUrl, String protocol) {
		this.username = username;
		this.password = password;
        this.redirectUrl = redirectUrl;
        this.protocol = protocol;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
