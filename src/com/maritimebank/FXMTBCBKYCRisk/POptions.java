package com.maritimebank.FXMTBCBKYCRisk;

import ru.inversion.bicomp.pref.Preference;
import ru.inversion.bicomp.pref.PreferenceType;
import ru.inversion.bicomp.pref.PriviledgeBundle;

import javax.persistence.Column;

public class POptions extends PriviledgeBundle {
	
	private String CDIROUTPBOX;
	
	public POptions(){}
	
	@Override
	public int[] getActionList() {
		return new int[] {
				 -15 // view
				,-16 // edit
		};
	}
	
	@Override
	public String[] getRoleList() {
		return new String [] {"ODB_ADM"};
	}
	
	@Preference(type= PreferenceType.STANDARD,dbName="USR.CDIROUTPBOX")
	@Column(name="CDIROUTPBOX")
	public String getCDIROUTPBOX() { return CDIROUTPBOX; }
	public void setCDIROUTPBOX(String val) { CDIROUTPBOX=val; }
}