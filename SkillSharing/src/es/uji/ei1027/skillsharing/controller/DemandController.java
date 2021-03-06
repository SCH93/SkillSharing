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

import es.uji.ei1027.skillsharing.dao.DemandDAO;
import es.uji.ei1027.skillsharing.model.Demand;
import es.uji.ei1027.skillsharing.validators.DemandValidator;

@Controller
@RequestMapping("/demand")
public class DemandController {

	private DemandDAO demandDao;
	
	
	@Autowired
	public void setDemandDao(DemandDAO demandDao) {
		
		this.demandDao = demandDao;
		
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
	public String mainDemands(Model model) {
		
		return "demand/main";
		
	}
	
	//----------- listado ------------------
	@RequestMapping("/list")
	public String listDemands(Model model) {
		
		model.addAttribute("demands", demandDao.getDemands());
		
		return "demand/list";
		
	}
	
	//----------- búsqueda unitaria ------------------
	@RequestMapping("/consult")
	public String consultDemand(Model model) {
		
		//Guardo en el atributo demand el objeto de tipo demand.
		model.addAttribute("demand", new Demand());
		
		return "demand/consult";
		
	}
	
	@RequestMapping(value="/consult", method=RequestMethod.POST)
	public String processConsultSubmit(@ModelAttribute("demand") Demand demand, BindingResult bindingResult, Model model) {
		
		DemandValidator demandValidator = new DemandValidator();
		
		demandValidator.setDemandDAO(demandDao);
		
		demandValidator.validateSearch(demand, bindingResult);
		
		if (bindingResult.hasErrors())
		
			return "demand/consult";
		
		model.addAttribute("demandResponse", demandDao.getDemand(demand));
		
		return "demand/consult";
	
	}
	
	//----------- añadir ------------------
	@RequestMapping("/add")
	public String addDemand(Model model) {
		
		model.addAttribute("demand", new Demand());
		
		return"demand/add";
		
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("demand") Demand demand, BindingResult bindingResult) {
		
		DemandValidator demandValidator = new DemandValidator();
		
		demandValidator.setDemandDAO(demandDao);
		
		demandValidator.validateAdd(demand, bindingResult);
		
		if (bindingResult.hasErrors())
			
			return "demand/add";
		
		demandDao.addDemand(demand);
		
		return "redirect:main.html";
		
	}
	
	//----------- actualización ------------------
	@RequestMapping("/update")
	public String editDemand(Model model) {
		
		model.addAttribute("demand", new Demand());
		
		return "demand/update";
		
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String processEditSubmit(@ModelAttribute("demand") Demand demand, BindingResult bindingResult) {
		
		DemandValidator demandValidator = new DemandValidator();
		
		demandValidator.setDemandDAO(demandDao);
		
		demandValidator.validateAdd(demand, bindingResult);
		
		if (bindingResult.hasErrors())
			
			return "demand/update";
		
		demandDao.updateDemand(demand);
		
		return "redirect:main.html";
	}
	
	//----------- eliminación ------------------
	@RequestMapping("/delete")
	public String deleteDemand(Model model) {
		
		model.addAttribute("demand", new Demand());
		
		return "demand/delete";
		
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String processDeleteSubmit(@ModelAttribute("demand") Demand demand, BindingResult bindingResult) {
		
		DemandValidator demandValidator = new DemandValidator();
		
		demandValidator.setDemandDAO(demandDao);
		
		demandValidator.validateSearch(demand, bindingResult);
		
		if (bindingResult.hasErrors())
			
			return "demand/delete";
		
		demandDao.deleteDemand(demand);
		
		return "redirect:main.html";
	
	}
	
}