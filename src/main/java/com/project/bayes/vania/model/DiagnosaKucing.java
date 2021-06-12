// Generated with g9.

package com.project.bayes.vania.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity(name = "diagnosa_kucing")
public class DiagnosaKucing implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false, precision = 10)
	private int id;
	@Column(name = "jenis_kelamin", length = 255)
	private String jenisKelamin;
	@Column(name = "gatal_gatal", length = 255)
	private String gatalGatal;
	@Column(name = "kulit_kemerahan", length = 255)
	private String kulitKemerahan;
	@Column(name = "bulu_rontok", length = 255)
	private String buluRontok;
	@Column(name = "kulit_kering", length = 255)
	private String kulitKering;
	@Column(length = 255)
	private String bengkak;
	@Column(length = 255)
	private String kropeng;
	@Column(length = 255)
	private String result;

	/** Default constructor. */
	public DiagnosaKucing() {
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
	 * Access method for kulitKemerahan.
	 *
	 * @return the current value of kulitKemerahan
	 */
	public String getKulitKemerahan() {
		return kulitKemerahan;
	}

	/**
	 * Setter method for kulitKemerahan.
	 *
	 * @param aKulitKemerahan the new value for kulitKemerahan
	 */
	public void setKulitKemerahan(String aKulitKemerahan) {
		kulitKemerahan = aKulitKemerahan;
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
	 * Access method for kulitKering.
	 *
	 * @return the current value of kulitKering
	 */
	public String getKulitKering() {
		return kulitKering;
	}

	/**
	 * Setter method for kulitKering.
	 *
	 * @param aKulitKering the new value for kulitKering
	 */
	public void setKulitKering(String aKulitKering) {
		kulitKering = aKulitKering;
	}

	/**
	 * Access method for bengkak.
	 *
	 * @return the current value of bengkak
	 */
	public String getBengkak() {
		return bengkak;
	}

	/**
	 * Setter method for bengkak.
	 *
	 * @param aBengkak the new value for bengkak
	 */
	public void setBengkak(String aBengkak) {
		bengkak = aBengkak;
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
	public void setResult(String aResult) {
		result = aResult;
	}

	/**
	 * Compares the key for this instance with another DiagnosaKucing.
	 *
	 * @param other The object to compare to
	 * @return True if other object is instance of class DiagnosaKucing and the key
	 *         objects are equal
	 */
	private boolean equalKeys(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DiagnosaKucing)) {
			return false;
		}
		DiagnosaKucing that = (DiagnosaKucing) other;
		if (this.getId() != that.getId()) {
			return false;
		}
		return true;
	}

	/**
	 * Compares this instance with another DiagnosaKucing.
	 *
	 * @param other The object to compare to
	 * @return True if the objects are the same
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof DiagnosaKucing))
			return false;
		return this.equalKeys(other) && ((DiagnosaKucing) other).equalKeys(this);
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
		return "DiagnosaKucing [id=" + id + ", jenisKelamin=" + jenisKelamin + ", gatalGatal=" + gatalGatal
				+ ", kulitKemerahan=" + kulitKemerahan + ", buluRontok=" + buluRontok + ", kulitKering=" + kulitKering
				+ ", bengkak=" + bengkak + ", kropeng=" + kropeng + ", result=" + result + "]";
	}

}
