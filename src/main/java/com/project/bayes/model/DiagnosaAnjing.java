// Generated with g9.

package com.project.bayes.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity(name = "diagnosa_anjing")
public class DiagnosaAnjing implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(name = "jenis_kelamin", length = 255)
	private String jenisKelamin;
	@Column(name = "gatal_gatal", length = 255)
	private String gatalGatal;
	@Column(name = "mengigit_gigit", length = 255)
	private String mengigitGigit;
	@Column(name = "menjilat_kaki", length = 255)
	private String menjilatKaki;
	@Column(name = "bulu_rontok", length = 255)
	private String buluRontok;
	@Column(name = "nafsu_makan", length = 255)
	private String nafsuMakan;
	@Column(length = 255)
	private String jamuran;
	@Column(length = 255)
	private String kropeng;
	@Column(length = 255)
	private String result;

	/** Default constructor. */
	public DiagnosaAnjing() {
		super();
	}

	/**
	 * Access method for id.
	 *
	 * @return the current value of id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter method for id.
	 *
	 * @param aId the new value for id
	 */
	public void setId(int aId) {
		id = aId;
	}

	/**
	 * Access method for jenisKelamin.
	 *
	 * @return the current value of jenisKelamin
	 */
	public String getJenisKelamin() {
		return jenisKelamin;
	}

	/**
	 * Setter method for jenisKelamin.
	 *
	 * @param aJenisKelamin the new value for jenisKelamin
	 */
	public void setJenisKelamin(String aJenisKelamin) {
		jenisKelamin = aJenisKelamin;
	}

	/**
	 * Access method for gatalGatal.
	 *
	 * @return the current value of gatalGatal
	 */
	public String getGatalGatal() {
		return gatalGatal;
	}

	/**
	 * Setter method for gatalGatal.
	 *
	 * @param aGatalGatal the new value for gatalGatal
	 */
	public void setGatalGatal(String aGatalGatal) {
		gatalGatal = aGatalGatal;
	}

	/**
	 * Access method for mengigitGigit.
	 *
	 * @return the current value of mengigitGigit
	 */
	public String getMengigitGigit() {
		return mengigitGigit;
	}

	/**
	 * Setter method for mengigitGigit.
	 *
	 * @param aMengigitGigit the new value for mengigitGigit
	 */
	public void setMengigitGigit(String aMengigitGigit) {
		mengigitGigit = aMengigitGigit;
	}

	/**
	 * Access method for menjilatKaki.
	 *
	 * @return the current value of menjilatKaki
	 */
	public String getMenjilatKaki() {
		return menjilatKaki;
	}

	/**
	 * Setter method for menjilatKaki.
	 *
	 * @param aMenjilatKaki the new value for menjilatKaki
	 */
	public void setMenjilatKaki(String aMenjilatKaki) {
		menjilatKaki = aMenjilatKaki;
	}

	/**
	 * Access method for buluRontok.
	 *
	 * @return the current value of buluRontok
	 */
	public String getBuluRontok() {
		return buluRontok;
	}

	/**
	 * Setter method for buluRontok.
	 *
	 * @param aBuluRontok the new value for buluRontok
	 */
	public void setBuluRontok(String aBuluRontok) {
		buluRontok = aBuluRontok;
	}

	/**
	 * Access method for nafsuMakan.
	 *
	 * @return the current value of nafsuMakan
	 */
	public String getNafsuMakan() {
		return nafsuMakan;
	}

	/**
	 * Setter method for nafsuMakan.
	 *
	 * @param aNafsuMakan the new value for nafsuMakan
	 */
	public void setNafsuMakan(String aNafsuMakan) {
		nafsuMakan = aNafsuMakan;
	}

	/**
	 * Access method for jamuran.
	 *
	 * @return the current value of jamuran
	 */
	public String getJamuran() {
		return jamuran;
	}

	/**
	 * Setter method for jamuran.
	 *
	 * @param aJamuran the new value for jamuran
	 */
	public void setJamuran(String aJamuran) {
		jamuran = aJamuran;
	}

	/**
	 * Access method for kropeng.
	 *
	 * @return the current value of kropeng
	 */
	public String getKropeng() {
		return kropeng;
	}

	/**
	 * Setter method for kropeng.
	 *
	 * @param aKropeng the new value for kropeng
	 */
	public void setKropeng(String aKropeng) {
		kropeng = aKropeng;
	}

	/**
	 * Access method for hasil.
	 *
	 * @return the current value of hasil
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Setter method for hasil.
	 *
	 * @param aHasil the new value for hasil
	 */
	public void setHasil(String aResult) {
		result = aResult;
	}

	/**
	 * Compares the key for this instance with another DiagnosaAnjing.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class DiagnosaAnjing and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DiagnosaAnjing)) {
			return false;
		}
		DiagnosaAnjing that = (DiagnosaAnjing) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another DiagnosaAnjing.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof DiagnosaAnjing))
			return false;
		return this.equalKeys(other) && ((DiagnosaAnjing) other).equalKeys(this);
	}

	/**
	 * Returns a hash code for this instance.
	 *
	 * @return Hash code
	 */
	@Override
	public int hashCode() {
		int i;
		int result = 17;
		i = getId();
		result = 37 * result + i;
		return result;
	}

	@Override
	public String toString() {
		return "DiagnosaAnjing [id=" + id + ", jenisKelamin=" + jenisKelamin + ", gatalGatal=" + gatalGatal
				+ ", mengigitGigit=" + mengigitGigit + ", menjilatKaki=" + menjilatKaki + ", buluRontok=" + buluRontok
				+ ", nafsuMakan=" + nafsuMakan + ", jamuran=" + jamuran + ", kropeng=" + kropeng + ", result=" + result
				+ "]";
	}

}
