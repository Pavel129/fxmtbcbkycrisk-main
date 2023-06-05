package com.maritimebank.FXMTBCBKYCRisk;

import java.math.BigDecimal;
import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;
import ru.inversion.db.entity.ProxyFor;

/**
@author  Grigorev
@since   2022/08/26 09:43:27
*/
@Entity (name="com.maritimebank.FXMTBCBKYCRisk.PAbbLov")
@NamedNativeQuery (name="com.maritimebank.FXMTBCBKYCRisk.PAbbLov", query="SELECT cabbabb, cabbcom FROM abb where cabbcol='cb_kyc_risk_act_tinf'")
public class PAbbLov implements Serializable
{
    private static final long serialVersionUID = 26_08_2022_09_43_27l;

    private String CABBABB;
    private String CABBCOM;

    public PAbbLov(){}

    @Id 
    @Column(name="CABBABB",nullable = false,length = 30)
    public String getCABBABB() {
        return CABBABB;
    }
    public void setCABBABB(String val) {
        CABBABB = val; 
    }
    @Column(name="CABBCOM",nullable = false,length = 1000)
    public String getCABBCOM() {
        return CABBCOM;
    }
    public void setCABBCOM(String val) {
        CABBCOM = val; 
    }
}