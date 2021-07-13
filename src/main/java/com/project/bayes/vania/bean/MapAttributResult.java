package com.project.bayes.vania.bean;

public class MapAttributResult {
	private String nameAttribute;
	private String valueAttriute;
	private String status;
	private long count;
	private double prob;
	private boolean isSelected;
	private boolean isZero;
	private long newCount;
	private double newProb;

	public MapAttributResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapAttributResult(String nameAttribute, String valueAttriute, String status, long count, double prob, boolean isSelected,
			boolean isZero, long newCount, double newProb) {
		super();
		this.nameAttribute = nameAttribute;
		this.valueAttriute = valueAttriute;
		this.status = status;
		this.count = count;
		this.prob = prob;
		this.isSelected = isSelected;
		this.isZero = isZero;
		this.newCount = newCount;
		this.newProb = newProb;
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

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isZero() {
		return isZero;
	}

	public void setZero(boolean isZero) {
		this.isZero = isZero;
	}

	public long getNewCount() {
		return newCount;
	}

	public void setNewCount(long newCount) {
		this.newCount = newCount;
	}

	public double getNewProb() {
		return newProb;
	}

	public void setNewProb(double newProb) {
		this.newProb = newProb;
	}

	@Override
	public String toString() {
		return "MapAttributResult [nameAttribute=" + nameAttribute + ", valueAttriute=" + valueAttriute + ", status=" + status + ", count="
				+ count + ", prob=" + prob + ", isSelected=" + isSelected + ", isZero=" + isZero + ", newCount=" + newCount + ", newProb="
				+ newProb + "]";
	}

}
