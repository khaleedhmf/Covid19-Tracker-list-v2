package com.ct.covid19Tracker.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.ct.covid19Tracker.bean.Covid19Bean;
import com.ct.covid19Tracker.bean.CovidBeanList;


@Service
public class Covid19DataService {


	private static String API_URL = "https://api.covid19india.org/raw_data.json";
	private List<Covid19Bean> beanList = new ArrayList<Covid19Bean>();
	private int totalNumberofCases;
	private String lastUpdated;
	private int newCases;
	private int deaths;
	private int numberOfCasesInTamilNadu;

	@PostConstruct
	@Scheduled(cron = "${application.cron.expression}")
	public void fetchPatientData() {
		List<Covid19Bean> newData = new ArrayList<Covid19Bean>();
		RestTemplate restTemplate = new RestTemplate();
		CovidBeanList response = restTemplate.getForObject(API_URL, CovidBeanList.class);
		newData = response.getRaw_data();
		totalNumberofCases = generateTotal(newData);
		lastUpdated = generateDateTime();
		newCases = findNewCases(newData);
		deaths = totalDeaths(newData);
		numberOfCasesInTamilNadu = getNumberOfCasesInTamiNadu(newData);
		this.beanList = newData;
	}

	private int getNumberOfCasesInTamiNadu(List<Covid19Bean> newData) {
		int count = 0;
		for (Covid19Bean covid19Bean : newData) {
			if(covid19Bean.getDetectedstate().equals("Tamil Nadu")) {
				count++;
			}
		}
		return count;
	}

	private int totalDeaths(List<Covid19Bean> newData) {
		int count = 0;
		for (Covid19Bean covid19Bean : newData) {
			if (covid19Bean.getCurrentstatus().equals("Deceased")) {
				count++;
			}
		}
		return count;
	}

	private int findNewCases(List<Covid19Bean> newData) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		int count = 0;
		for (Covid19Bean covid19Bean : newData) {
			if (covid19Bean.getDateannounced().equals(strDate)) {
				count++;
			}
		}
		return count;
	}

	private String generateDateTime() {
//		Date date = java.util.Calendar.getInstance().getTime();
//		return date;
		Date today = new Date();
		DateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String ISTDate = df.format(today);
        return ISTDate;
	}

	private int generateTotal(List<Covid19Bean> newData) {
		int count = 0;
		for (Covid19Bean covid19Bean : newData) {
			if (covid19Bean.getDateannounced() != "") {
 				count++;
			}
		}
		return count;
	}

//	private List<Covid19Bean> getListOfBean(String array) {
//		List<Covid19Bean> listBean = new ArrayList<>();
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		try {
//			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//			listBean = objectMapper.readValue(array, new TypeReference<List<Covid19Bean>>() {
//			});
//
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//		}
//		return listBean;
//	}

	public List<Covid19Bean> getBeanList() {
		return beanList;
	}

	public int getTotalNumberofCases() {
		return totalNumberofCases;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public int getNewCases() {
		return newCases;
	}

	public int getDeaths() {
		return deaths;
	}

	public int getNumberOfCasesInTamilNadu() {
		return numberOfCasesInTamilNadu;
	}

}
