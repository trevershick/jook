package com.railinc.jook.web.secure.jook;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.railinc.jook.JookManagementService;
import com.railinc.jook.domain.JookInteractionProvider;
import com.railinc.jook.web.util.SetOfStringEditor;
import com.railinc.jook.web.util.StandardController;

@Controller
@RequestMapping("secure/jook/providers")
public class Providers extends StandardController {
	public static final String VIEW_PROVIDER_LIST = ".view.providerList";
	private static final String REDIRECT_LIST = "redirect:list";
	public static final String VIEW_PROVIDER_FORM = ".view.providerForm";
	public static final String VIEW_PROVIDER_DETAILS = ".view.providerDetails";
	
	
	JookManagementService service = null;


	public JookManagementService getService() {
		return service;
	}


	public void setService(JookManagementService service) {
		this.service = service;
	}
	

	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Set.class, "applications", new SetOfStringEditor());
	}

	@ModelAttribute("totalProviderCount")
	public Long totalCount() {
		return getService().registeredProviderCount();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="details")
	public String showProductDetails(@RequestParam("name") String name, Model model)  {
		JookInteractionProvider productByName = service.getProvider(name);
		 
		model.addAttribute("provider", productByName);

		
		return VIEW_PROVIDER_DETAILS;
	}

	
	// show new form
	@RequestMapping(method=RequestMethod.GET,params="!name",value="update")
	public String showProductForm(@ModelAttribute("provider") JookInteractionProvider provider, Model model) {
		return VIEW_PROVIDER_FORM;
	}	
	
	// show edit form
	@RequestMapping(method=RequestMethod.GET,value="update")
	public String showExistingProviderForm(@ModelAttribute("provider") JookInteractionProvider provider, Model model)  {
		JookInteractionProvider productByName = service.getProvider(provider.getName());
		model.addAttribute("provider", productByName);
		return VIEW_PROVIDER_FORM;
	}



	@RequestMapping(method=RequestMethod.POST,value="update",params="_eventId_cancel")
	public String cancelUpdate(ModelMap map, HttpServletRequest r) {
		message(r,"No action has been taken");
		return REDIRECT_LIST;
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="update",params="_eventId_save")
	public String saveProduct(@ModelAttribute("provider") JookInteractionProvider value, BindingResult result, HttpServletRequest r) {
		JookInteractionProvider p = getService().getProvider(value.getName());
		
		ValidationUtils.rejectIfEmpty(result, "name", "required");
		
		
		if (result.hasErrors()) {
			return VIEW_PROVIDER_FORM;
		}
		
		if (p != null) {
			p.setUnsecureUrl(value.getUnsecureUrl());
			p.setSecureUrl(value.getSecureUrl());
			p.setActive(value.getActive());
		} else {
			p = value;
		}
		getService().updateProvider(p);
		message(r, String.format("Successfully saved the provider '%s'", p.getName()));
		
		return REDIRECT_LIST;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="update",params="_eventId_delete")
	public String deleteProduct(@ModelAttribute("provider") final JookInteractionProvider value, ModelMap map, HttpServletRequest r) {
		boolean ok = doQuietly(new WebCommand() {
			@Override
			public void doIt() {
				JookInteractionProvider p = getService().getProvider(value.getName());
				getService().deleteProvider(p);
			}
		}, r);
		if (ok) {
			message(r, "The product was deleted.");
			return REDIRECT_LIST;
		} else {
			return VIEW_PROVIDER_FORM;
		}
	}
		
	@RequestMapping(method=RequestMethod.GET,value={""})
	public String allProductsR(Model model) {
		return "redirect:providers/list";
	}	
	
	@RequestMapping(method=RequestMethod.GET,value={"list",""})
	public String allProducts(Model model) {
		List<JookInteractionProvider> providers = service.registeredProviders(0, Integer.MAX_VALUE, null, true);
		model.addAttribute("providers", providers);

		return VIEW_PROVIDER_LIST;
	}
	

	
}
