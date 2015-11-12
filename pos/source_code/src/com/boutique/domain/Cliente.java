package com.boutique.domain;

import java.io.*;
import java.sql.*;

public class Cliente {
	private int id;
	private String email = "";
	private String rfc = "";
	private String nombre = "";
	private String calle = "";
	private String numero = "";
	private String numeroInterior = "";
	private String calleAledanaA = "";
	private String calleAledanaB = "";
	private String colonia = "";
	private String telefono = "";
	private String celular = "";
	private Date fechaNacimiento;
	private String tallaCalzado = "";
	private String tallaConjunto = "";
	private String tieneCredito = "";
	private double limiteCredito;
	private String estadoCivil = "";
	private String casaPropia = "";
	private String trabaja = "";
	private String ocupacion = "";
	private String domicilioTrabajo = "";
	private String nombreEmpresa = "";
	private String nombreConyugue = "";
	private String ocupacionConyugue = "";
	private String domicilioTrabajoConyugue = "";
	private String personal1 = "";
	private String personal2 = "";
	private String comercial1 = "";
	private String comercial2 = "";
	private String comercial3 = "";
	private String apellidoPaterno = "";
	private String apellidoMaterno = "";
	private String telefonoTrabajo = "";
	private String familiar1 = "";
	private String familiar2 = "";
	private String claveCliente = "";
	private String nombreEmpresaConyugue = "";
	private String noEmail = "";
	private ClasificacionCliente clasificacionCliente;
	private InputStream fotografia;
	private Timestamp lastUpdate;

	public Cliente() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setCalleAledanaA(String calleAledanaA) {
		this.calleAledanaA = calleAledanaA;
	}

	public void setCalleAledanaB(String calleAledanaB) {
		this.calleAledanaB = calleAledanaB;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setTallaCalzado(String tallaCalzado) {
		this.tallaCalzado = tallaCalzado;
	}

	public void setTallaConjunto(String tallaConjunto) {
		this.tallaConjunto = tallaConjunto;
	}

	public void setTieneCredito(String tieneCredito) {
		this.tieneCredito = tieneCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public void setCasaPropia(String casaPropia) {
		this.casaPropia = casaPropia;
	}

	public void setTrabaja(String trabaja) {
		this.trabaja = trabaja;
	}

	public void setDomicilioTrabajo(String domicilioTrabajo) {
		this.domicilioTrabajo = domicilioTrabajo;
	}

	public void setNombreConyugue(String nombreConyugue) {
		this.nombreConyugue = nombreConyugue;
	}

	public void setOcupacionConyugue(String ocupacionConyugue) {
		this.ocupacionConyugue = ocupacionConyugue;
	}

	public void setDomicilioTrabajoConyugue(String domicilioTrabajoConyugue) {
		this.domicilioTrabajoConyugue = domicilioTrabajoConyugue;
	}

	public void setPersonal1(String personal1) {

		this.personal1 = personal1;
	}

	public void setPersonal2(String personal2) {
		this.personal2 = personal2;
	}

	public void setComercial1(String comercial1) {
		this.comercial1 = comercial1;
	}

	public void setComercial2(String comercial2) {
		this.comercial2 = comercial2;
	}

	public void setComercial3(String comercial3) {
		this.comercial3 = comercial3;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}

	public void setFamiliar2(String familiar2) {
		this.familiar2 = familiar2;
	}

	public void setFamiliar1(String familiar1) {
		this.familiar1 = familiar1;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setClaveCliente(String claveCliente) {
		this.claveCliente = claveCliente;
	}

	public void setNombreEmpresaConyugue(String nombreEmpresaConyugue) {
		this.nombreEmpresaConyugue = nombreEmpresaConyugue;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNoEmail(String noEmail) {
		this.noEmail = noEmail;
	}

	public void setClasificacionCliente(
			ClasificacionCliente clasificacionCliente) {
		this.clasificacionCliente = clasificacionCliente;
	}

	public void setFotografia(InputStream fotografia) {
		this.fotografia = fotografia;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCalle() {
		return calle;
	}

	public String getNumero() {
		return numero;
	}

	public String getCalleAledanaA() {
		return calleAledanaA;
	}

	public String getCalleAledanaB() {
		return calleAledanaB;
	}

	public String getColonia() {
		return colonia;
	}

	public String getTelefono() {
		return telefono;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getTallaCalzado() {
		return tallaCalzado;
	}

	public String getTallaConjunto() {
		return tallaConjunto;
	}

	public String getTieneCredito() {
		return tieneCredito;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public String getCasaPropia() {
		return casaPropia;
	}

	public String getTrabaja() {
		return trabaja;
	}

	public String getDomicilioTrabajo() {
		return domicilioTrabajo;
	}

	public String getNombreConyugue() {
		return nombreConyugue;
	}

	public String getOcupacionConyugue() {
		return ocupacionConyugue;
	}

	public String getDomicilioTrabajoConyugue() {
		return domicilioTrabajoConyugue;
	}

	public String getPersonal1() {

		return personal1;
	}

	public String getPersonal2() {
		return personal2;
	}

	public String getComercial1() {
		return comercial1;
	}

	public String getComercial2() {
		return comercial2;
	}

	public String getComercial3() {
		return comercial3;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}

	public String getFamiliar1() {
		return familiar1;
	}

	public String getFamiliar2() {
		return familiar2;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public String getNumeroInterior() {
		return numeroInterior;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public String getCelular() {
		return celular;
	}

	public String getClaveCliente() {
		return claveCliente;
	}

	public String getNombreEmpresaConyugue() {
		return nombreEmpresaConyugue;
	}

	public String getEmail() {
		return email;
	}

	public String getNoEmail() {
		return noEmail;
	}

	public ClasificacionCliente getClasificacionCliente() {
		return clasificacionCliente;
	}

	public InputStream getFotografia() {
		return fotografia;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getRfc() {
		return rfc;
	}

}
