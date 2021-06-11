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

@Entity(name = "data_bayes")
public class DataBayes implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 19)
	private long id;
	@Column(name = "jenis_kelamin", length = 255)
	private String jenisKelamin;
	@Column(length = 255)
	private String jamuran;
	@Column(name = "gatal_gatal", length = 255)
	private String gatalGatal;
	@Column(length = 255)
	private String rontok;
	@Column(name = "garuk_garuk_telinga", length = 255)
	private String garukGarukTelinga;
	@Column(length = 255)
	private String kropeng;
	@Column(name = "nafsu_makan", length = 255)
	private String nafsuMakan;
	@Column(length = 255)
	private String status;

	/** Default constructor. */
	public DataBayes() {
		super();
	}

	/**
	 * Access method for id.
	 *
	 * @return the current value of id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter method for id.
	 *
	 * @param aId the new value for id
	 */
	public void setId(long aId) {
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
	 * Access method for rontok.
	 *
	 * @return the current value of rontok
	 */
	public String getRontok() {
		return rontok;
	}

	/**
	 * Setter method for rontok.
	 *
	 * @param aRontok the new value for rontok
	 */
	public void setRontok(String aRontok) {
		rontok = aRontok;
	}

	/**
	 * Access method for garukGarukTelinga.
	 *
	 * @return the current value of garukGarukTelinga
	 */
	public String getGarukGarukTelinga() {
		return garukGarukTelinga;
	}

	/**
	 * Setter method for garukGarukTelinga.
	 *
	 * @param aGarukGarukTelinga the new value for garukGarukTelinga
	 */
	public void setGarukGarukTelinga(String aGarukGarukTelinga) {
		garukGarukTelinga = aGarukGarukTelinga;
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
	 * Access method for status.
	 *
	 * @return the current value of status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Setter method for status.
	 *
	 * @param aStatus the new value for status
	 */
	public void setStatus(String aStatus) {
		status = aStatus;
	}

	/**
	 * Compares the key for this instance with another DataBayes.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class DataBayes and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DataBayes)) {
			return false;
		}
		DataBayes that = (DataBayes) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another DataBayes.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof DataBayes))
			return false;
		return this.equalKeys(other) && ((DataBayes) other).equalKeys(this);
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
		i = (int) (getId() ^ (getId() >>> 32));
		result = 37 * result + i;
		return result;
	}

	/**
	 * Returns a debug-friendly String representation of this instance.
	 *
	 * @return String representation of this instance
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("[DataBayes |");
		sb.append(" id=").append(getId());
		sb.append("]");
		return sb.toString();
	}

}
