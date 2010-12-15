package com.railinc.jook.web.interactions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.railinc.jook.interaction.JookInteraction;

public interface JookInteractionFactory {
	List<? extends JookInteraction> interactions(HttpServletRequest request);
}
