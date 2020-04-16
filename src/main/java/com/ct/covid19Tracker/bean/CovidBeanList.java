package com.ct.covid19Tracker.bean;

import java.util.List;


public class CovidBeanList {
	
	private List<Covid19Bean> raw_data;

	public List<Covid19Bean> getRaw_data() {
		return raw_data;
	}

	public void setRaw_data(List<Covid19Bean> raw_data) {
		this.raw_data = raw_data;
	}

	public CovidBeanList(List<Covid19Bean> raw_data) {
		this.raw_data = raw_data;
	}
	

	public CovidBeanList() {
	}

	@Override
	public String toString() {
		return "CovidBeanList [raw_data=" + raw_data + "]";
	}
	
	
	

}
