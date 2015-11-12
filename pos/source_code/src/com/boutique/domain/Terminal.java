package com.boutique.domain;

/**
 * Represents a Terminal POS in a Store.
 * 
 * @author aldo
 * 
 */
public class Terminal {
	private int id;
	private String noTerminal;
	private int idBoutique;
	private String description;
	private boolean activa;
	private boolean legacy;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setNoTerminal(String noTerminal) {
		this.noTerminal = noTerminal;
	}

	public String getNoTerminal() {
		return noTerminal;
	}

	public void setIdBoutique(int idBoutique) {
		this.idBoutique = idBoutique;
	}

	public int getIdBoutique() {
		return idBoutique;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setLegacy(boolean legacy) {
		this.legacy = legacy;
	}

	public boolean isLegacy() {
		return legacy;
	}
}
