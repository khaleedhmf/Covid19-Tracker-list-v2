package com.ct.covid19Tracker;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.ct.covid19Tracker.bean.Covid19Bean;
import com.ct.covid19Tracker.services.Covid19DataService;

@SpringBootTest
public class Covid19TrackerApplicationTests {
	RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

	Covid19DataService service = Mockito.mock(Covid19DataService.class);
	Covid19Bean covid19Bean = Mockito.mock(Covid19Bean.class);
	long total;

	List<Covid19Bean> mockedList = Mockito.mock(List.class);

	String json = "{\n" + "	\"raw_data\": [\n" + "		{\n" + "			\"agebracket\": \"20\",\n"
			+ "			\"backupnotes\": \"Student from Wuhan\",\n"
			+ "			\"contractedfromwhichpatientsuspected\": \"\",\n"
			+ "			\"currentstatus\": \"Recovered\",\n" + "			\"dateannounced\": \"30/01/2020\",\n"
			+ "			\"detectedcity\": \"Thrissur\",\n" + "			\"detecteddistrict\": \"Thrissur\",\n"
			+ "			\"detectedstate\": \"Kerala\",\n" + "			\"estimatedonsetdate\": \"\",\n"
			+ "			\"gender\": \"F\",\n" + "			\"nationality\": \"India\",\n"
			+ "			\"notes\": \"Travelled from Wuhan\",\n" + "			\"patientnumber\": \"1\",\n"
			+ "			\"source1\": \"https://twitter.com/vijayanpinarayi/status/1222819465143832577\",\n"
			+ "			\"source2\": \"https://weather.com/en-IN/india/news/news/2020-02-14-kerala-defeats-coronavirus-indias-three-covid-19-patients-successfully\",\n"
			+ "			\"source3\": \"\",\n" + "			\"statecode\": \"KL\",\n"
			+ "			\"statepatientnumber\": \"KL-TS-P1\",\n" + "			\"statuschangedate\": \"14/02/2020\",\n"
			+ "			\"typeoftransmission\": \"Imported\"\n" + "		}\n" + "]\n" + "}";

	@Test
	public void test_getBeanList() {
		when(service.getBeanList()).thenReturn(mockedList);
		when(covid19Bean.getPatientnumber()).thenReturn("1");
		assertEquals("1", covid19Bean.getPatientnumber());
		assertEquals(mockedList.size(), service.getBeanList().size());
		verify(service).getBeanList();

	}

	@Test
	public void test_getTotalNumberofCases() {
		when(service.getTotalNumberofCases()).thenReturn(125);
		assertEquals(125, service.getTotalNumberofCases());
		verify(service).getTotalNumberofCases();

	}

	@Test
	public void test_getNewCases() {
		when(service.getNewCases()).thenReturn(25);
		assertEquals(25, service.getNewCases());
		verify(service).getNewCases();

	}

	@Test
	public void test_getLastUpdated() {
		Date today = new Date();
		DateFormat df = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		String date = df.format(today);
		when(service.getLastUpdated()).thenReturn(date);
		assertEquals(date, service.getLastUpdated());
		verify(service).getLastUpdated();

	}

	@Test
	public void test_getNumberOfCasesInTamiNadu() {
		when(service.getNumberOfCasesInTamilNadu()).thenReturn(23);
		assertEquals(23, service.getNumberOfCasesInTamilNadu());
		verify(service).getNumberOfCasesInTamilNadu();

	}

}
