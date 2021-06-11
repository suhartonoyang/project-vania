package com.project.bayes.bean;

public class MapAttributStatus {
	private String nameAttribute;
	private String valueAttriute;
	private String status;
	private long count;
	private double prob;

	public MapAttributStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapAttributStatus(String nameAttribute, String valueAttriute, String status, long count, double prob) {
		super();
		this.nameAttribute = nameAttribute;
		this.valueAttriute = valueAttriute;
		this.status = status;
		this.count = count;
		this.prob = prob;
	}

	public String getNameAttribute() {
		return nameAttribute;
	}

	public void setNameAttribute(String nameAttribute) {
		this.nameAttribute = nameAttribute;
	}

	public String getValueAttriute() {
		return valueAttriute;
	}

	public void setValueAttriute(String valueAttriute) {
		this.valueAttriute = valueAttriute;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public double getProb() {
		return prob;
	}

	public void setProb(double prob) {
		this.prob = prob;
	}

	@Override
	public String toString() {
		return "MapAttributStatus [nameAttribute=" + nameAttribute + ", valueAttriute=" + valueAttriute + ", status="
				+ status + ", count=" + count + ", prob=" + prob + "]";
	}

}
