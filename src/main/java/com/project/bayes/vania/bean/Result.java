package com.project.bayes.vania.bean;

public class Result {
	private String name;
	private double value;
	private String journey;

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(String name, double value, String journey) {
		super();
		this.name = name;
		this.value = value;
		this.journey = journey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getJourney() {
		return journey;
	}

	public void setJourney(String journey) {
		this.journey = journey;
	}

	@Override
	public String toString() {
		return "Result [name=" + name + ", value=" + value + ", journey=" + journey + "]";
	}

}
