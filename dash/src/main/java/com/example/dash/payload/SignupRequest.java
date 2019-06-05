package com.example.dash.payload;

// Request format for signup
public class SignupRequest {

    private String name;
    private String username;

    public SignupRequest() {}

    public SignupRequest(String name, String username) {
        this.name = name;
        this.username = username;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
