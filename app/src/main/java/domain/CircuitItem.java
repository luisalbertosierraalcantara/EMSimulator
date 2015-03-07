package domain;

import android.content.Context;

public enum CircuitItem {
	DC_GENERATOR ("dc_generator"),
	AC_GENERATOR("ac_generator"),
	RESISTANCE("resistance"),
	CONDENSER("condenser"),
	SELF_INDUCTION("self_induction"),
	MOTOR("motor"),
	SWITCH1("switch_1"),
	SWITCH2("switch_2"),
	DIODE("diode"),
	ZENER_DIODE("zener_diode"),
	AMMETER("ammeter"),
	VOLTMETER("voltmeter");
	
	private final String id;
	
	private CircuitItem(String imageFile) {
		this.id = imageFile;
	}
	
	public int getImageResource(Context context) {
		return context.getResources().getIdentifier(id, "drawable", context.getPackageName());
	}
	
	public String getString(Context context) {
		return (String) context.getResources().getText(context.getResources().getIdentifier(id, "string", context.getPackageName()));
	}
	
	public String getDescription(Context context) {
		return (String) context.getResources().getText(context.getResources().getIdentifier(id+"_description", "string", context.getPackageName()));
	}
}
