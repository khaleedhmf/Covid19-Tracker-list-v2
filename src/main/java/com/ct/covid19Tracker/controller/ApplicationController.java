package com.ct.covid19Tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.ct.covid19Tracker.services.Covid19DataService;

@Controller
public class ApplicationController {
	
	@Autowired
	Covid19DataService covid19DataService;
	
	
	
	@GetMapping
	public String covid19UiPage(Model model) throws Exception{
		model.addAttribute("Covid19Data", covid19DataService.getBeanList());
		model.addAttribute("TotalNumber", covid19DataService.getTotalNumberofCases());
		model.addAttribute("LastUpdatedOn", covid19DataService.getLastUpdated());
		model.addAttribute("TodayReportedNewCases", covid19DataService.getNewCases());
		model.addAttribute("TotalDeaths", covid19DataService.getDeaths());
		model.addAttribute("CasesReportedInTN", covid19DataService.getNumberOfCasesInTamilNadu());
		return "Covid19";
		
	}

}
