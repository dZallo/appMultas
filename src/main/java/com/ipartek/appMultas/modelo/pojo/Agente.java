package com.ipartek.appMultas.modelo.pojo;

import java.util.HashMap;

public class Agente {
	private Long id;
	private String nombre;
	private String placa;
	private Long id_departamento;
	private HashMap<Long, Multa> multas;
	private Long importeAnual;
	private Long importeMensual;

	public Agente() {
		super();
	}

	public Agente(Long id, String nombre, String placa, Long id_departamento, HashMap<Long, Multa> multas) {
		this();
		setId(id_departamento);
		setNombre(nombre);
		setPlaca(placa);
		setId_departamento(id_departamento);
		setMultas(multas);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Long getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(Long id_departamento) {
		this.id_departamento = id_departamento;
	}

	public HashMap<Long, Multa> getMultas() {
		return multas;
	}

	public void setMultas(HashMap<Long, Multa> multas) {
		this.multas = multas;
	}
	

	public Long getImporteAnual() {
		return importeAnual;
	}

	public void setImporteAnual(Long importeAnual) {
		this.importeAnual = importeAnual;
	}

	public Long getImporteMensual() {
		return importeMensual;
	}

	public void setImporteMensual(Long importeMensual) {
		this.importeMensual = importeMensual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_departamento == null) ? 0 : id_departamento.hashCode());
		result = prime * result + ((multas == null) ? 0 : multas.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agente other = (Agente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_departamento == null) {
			if (other.id_departamento != null)
				return false;
		} else if (!id_departamento.equals(other.id_departamento))
			return false;
		if (multas == null) {
			if (other.multas != null)
				return false;
		} else if (!multas.equals(other.multas))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Agente [id=" + id + ", nombre=" + nombre + ", placa=" + placa + ", id_departamento=" + id_departamento
				+ ", multas=" + multas + ", importeAnual=" + importeAnual + ", importeMensual=" + importeMensual + "]";
	}



}
