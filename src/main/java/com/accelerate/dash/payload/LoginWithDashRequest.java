/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 09 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

// Request format for the Login With Dash feature
public class LoginWithDashRequest {

    private String username;

    private String password;

    private String redirectUrl;

    private String protocol;

    private String reference;

    public LoginWithDashRequest() {}

	public LoginWithDashRequest(String username, String password, String redirectUrl, String protocol, String reference) {
		this.username = username;
		this.password = password;
        this.redirectUrl = redirectUrl;
        this.protocol = protocol;
        this.reference = reference;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
