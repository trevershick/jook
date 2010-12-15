package com.railinc.jook;

import java.util.List;

import com.railinc.jook.domain.JookInteractionProvider;
import com.railinc.jook.domain.StaticInteraction;
import com.railinc.jook.interaction.JookInteraction;

public interface JookManagementService {
	
	
	List<JookInteractionProvider> registeredProviders(int start, int count, String sortColumn, boolean ascending);
	long registeredProviderCount();
	
	JookInteractionProvider getProvider(String name);
	JookInteractionProvider createProvider(String name, String url,String[] apps);
	void updateProvider(JookInteractionProvider p);
	void deleteProvider(JookInteractionProvider f);
	void updateInteraction(StaticInteraction p);
	void deleteInteraction(StaticInteraction p);
	StaticInteraction getInteraction(Long id);
	List<StaticInteraction> interactions(int i, int maxValue, Object object,
			boolean b);
	List<? extends JookInteraction> active();
	
	
}
