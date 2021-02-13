package br.cefetrj.mg.bsi.vocealuga.config;

public enum ConnectionType {

	 EXTERNAL("database-external.properties"), DEFAULT("database.properties");
	 public String value;

	 ConnectionType(String string) {
		this.value = string;
	}
	public String getValue() {
		return value;
	}
}