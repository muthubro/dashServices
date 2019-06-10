/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 09 June 2019
 * Modified Date	: 09 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.payload;

// Option structure in a poll request
public class PollOptionRequest {

    private int optionId;

    private String optionText;

    public PollOptionRequest() {}

	public PollOptionRequest(int optionId, String optionText) {
		this.optionId = optionId;
		this.optionText = optionText;
	}

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}
