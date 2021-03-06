package es.uji.ei1027.skillsharing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.skillsharing.dao.OfferDAO;
import es.uji.ei1027.skillsharing.model.Offer;
import es.uji.ei1027.skillsharing.validators.OfferValidator;

@Controller
@RequestMapping("/offer")
public class OfferController {
	
	private OfferDAO offerDao;
	
	
	@Autowired
	public void setOfferDao(OfferDAO offerDao) {
		
		this.offerDao = offerDao;
	
	}
	
	//Método para convertir el formato String a Date en el formulario
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	  
	    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm"); 
	    binder.registerCustomEditor(Date.class, "hours", new CustomDateEditor(timeFormat, false));

	}
	
	//---------- página de métodos ---------
	
	@RequestMapping("/main")
	public String mainOffers(Model model) {
		
		return "offer/main";
		
	}
	
	//----------- listado ------------------
	@RequestMapping("/list")
	public String listOffers(Model model) {
		
		model.addAttribute("offers", offerDao.getOffers());
		
		return "offer/list";
		
	}
	
	//----------- búsqueda unitaria ------------------
	@RequestMapping("/consult")
	public String consultOffer(Model model) {
		
		//Guardo en el atributo offer el objeto de tipo offer.
		model.addAttribute("offer", new Offer());
		
		return "offer/consult";
		
	}
	
	@RequestMapping(value="/consult", method=RequestMethod.POST)
	public String processConsultSubmit(@ModelAttribute("offer") Offer offer, BindingResult bindingResult, Model model) {
		
		OfferValidator offerValidator = new OfferValidator();
		
		offerValidator.setOfferDAO(offerDao);
		
		offerValidator.validateSearch(offer, bindingResult);
		
		if (bindingResult.hasErrors())
		
			return "offer/consult";
		
		model.addAttribute("offerResponse", offerDao.getOffer(offer));
		
		return "offer/consult";
	
	}
	
	//----------- añadir ------------------
	@RequestMapping("/add")
	public String addOffer(Model model) {
		
		model.addAttribute("offer", new Offer());
		
		return"offer/add";
		
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("offer") Offer offer, BindingResult bindingResult) {
		
		OfferValidator offerValidator = new OfferValidator();
		
		offerValidator.setOfferDAO(offerDao);
		
		offerValidator.validateAdd(offer, bindingResult);
		
		if (bindingResult.hasErrors())
			
			return "offer/add";
		
		offerDao.addOffer(offer);
		
		return "redirect:main.html";
		
	}
	
	//----------- actualización ------------------
	@RequestMapping("/update")
	public String editOffer(Model model) {
		
		model.addAttribute("offer", new Offer());
		
		return "offer/update";
		
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String processEditSubmit(@ModelAttribute("offer") Offer offer, BindingResult bindingResult) {
		
		OfferValidator offerValidator = new OfferValidator();
		
		offerValidator.setOfferDAO(offerDao);
		
		offerValidator.validateAdd(offer, bindingResult);
		
		if (bindingResult.hasErrors())
			
			return "offer/update";
		
		offerDao.updateOffer(offer);
		
		return "redirect:main.html";
	}
	
	//----------- eliminación ------------------
	@RequestMapping("/delete")
	public String deleteOffer(Model model) {
		
		model.addAttribute("offer", new Offer());
		
		return "offer/delete";
		
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String processDeleteSubmit(@ModelAttribute("offer") Offer offer, BindingResult bindingResult) {
		
		OfferValidator offerValidator = new OfferValidator();
		
		offerValidator.setOfferDAO(offerDao);
		
		offerValidator.validateSearch(offer, bindingResult);
		
		if (bindingResult.hasErrors())
			
			return "offer/delete";
		
		offerDao.deleteOffer(offer);
		
		return "redirect:main.html";
	
	}
	
}