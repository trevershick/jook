package com.railinc.jook.web.interactions;

import com.railinc.jook.interaction.JookInteraction;

public class JookInteractionVO implements JookInteraction {
	private String type, title, url;
	private boolean shake;

	public JookInteractionVO(String type, String title, String url,
			boolean shake) {
		this.type = type;
		this.title = title;
		this.url = url;
		this.shake = shake;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public boolean shake() {
		return shake;
	}

}
