package com.thulasi.easyway.type;

public enum MeasurementUnit {

	PCS("Pcs"), PACKET("Packet"), KG("Kilogram"), LITER("Liter"), BOX("Box"), DOZEN("Dozen");

	private final String label;

	MeasurementUnit(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
