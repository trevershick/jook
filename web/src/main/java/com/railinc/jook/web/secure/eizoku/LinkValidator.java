package com.railinc.jook.web.secure.eizoku;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.railinc.jook.domain.Link;

public class LinkValidator implements Validator {
	@Override
	public boolean supports(Class<?> arg0) {
		return arg0 == Link.class;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Link impl = (Link) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "path", "field.required");
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "url", "field.required");
		
		String regex = "[0-9a-zA-Z_-]+";
		if (!arg1.hasFieldErrors("path") && !impl.getPath().matches(regex)) {
			arg1.rejectValue("path", "field.mustmatchpattern",new Object[]{regex,impl.getPath()},  null);
		}
		
		String urlregex = "(https?://[\\w\\d\\.]+)?/[\\w\\d:#@%/;$()~_?\\+-=\\.&]*";
		if (!arg1.hasFieldErrors("url") && !impl.getUrl().matches(urlregex)) {
			arg1.rejectValue("url", "field.mustmatchpattern",new Object[]{regex,impl.getUrl()},  null);
		}

	}

}
