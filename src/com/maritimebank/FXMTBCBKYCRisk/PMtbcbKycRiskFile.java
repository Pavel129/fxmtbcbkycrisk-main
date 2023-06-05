package com.maritimebank.FXMTBCBKYCRisk;

import java.sql.*;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;

/**
@author  Grigorev
@since   2022/04/18 10:30:59
*/
@Entity (name="com.maritimebank.FXMTBCBKYCRisk.PMtbcbKycRiskFile")
@Table (name="MTB.CB_KYC_RISK_FILE")
public class PMtbcbKycRiskFile implements Serializable
{
    private static final long serialVersionUID = 18_04_2022_10_30_59L;

    private Long ICKRFID;
    private LocalDate DCKRFDATE;
    private String CCKRFNAME;
    private Blob ZCKRFBODY;

    public PMtbcbKycRiskFile(){}

    @Id 
    @Column(name="ICKRFID",nullable = false,length = 0)
    public Long getICKRFID() {
        return ICKRFID;
    }
    public void setICKRFID(Long val) {
        ICKRFID = val; 
    }
    @Column(name="DCKRFDATE")
    public LocalDate getDCKRFDATE() {
        return DCKRFDATE;
    }
    public void setDCKRFDATE(LocalDate val) {
        DCKRFDATE = val; 
    }
    @Column(name="CCKRFNAME")
    public String getCCKRFNAME() {
        return CCKRFNAME;
    }
    public void setCCKRFNAME(String val) {
        CCKRFNAME = val; 
    }
    @Column(name="ZCKRFBODY")
    public Blob getZCKRFBODY() {
        return ZCKRFBODY;
    }
    public void setZCKRFBODY(Blob val) {
        ZCKRFBODY = val;
    }
    
}