/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 09 June 2019
 * Modified Date	: 09 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.model;

import javax.validation.constraints.NotBlank;

public class PollOption {

    @NotBlank
    private int optionId;

    @NotBlank
    private String optionText;

    public PollOption() {}

	public PollOption(int optionId, String optionText) {
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
