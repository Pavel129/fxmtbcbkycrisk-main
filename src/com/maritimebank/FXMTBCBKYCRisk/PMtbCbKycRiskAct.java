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
@since   2022/08/25 19:03:16
*/
@Entity (name="com.maritimebank.FXMTBCBKYCRisk.PMtbCbKycRiskAct")
@NamedNativeQuery (name="com.maritimebank.FXMTBCBKYCRisk.PMtbCbKycRiskAct", query="SELECT ICKRAID,ICUSNUM,CCUSNUMNAL,CCUSNAME_SH,DACTION,DINFORM,TINFORM,TINFDESCR FROM mtb.v_cb_kyc_risk_act")
public class PMtbCbKycRiskAct extends IDMarkable implements Serializable
{
    private static final long serialVersionUID = 25_08_2022_19_03_17L;

    private Long ICKRAID;
    private Long ICUSNUM;
    private LocalDate DACTION;
    private LocalDate DINFORM;
    private Long TINFORM;
    private String CCUSNUMNAL;
    private String CCUSNAME_SH;
    private String TINFDESCR;

    public PMtbCbKycRiskAct(){}

    @Id 
    @Column(name="ICKRAID",nullable = false,length = 0)
    public Long getICKRAID() {
        return ICKRAID;
    }
    public void setICKRAID(Long val) {
        ICKRAID = val; 
    }
    @Column(name="ICUSNUM",nullable = false,length = 0)
    public Long getICUSNUM() {
        return ICUSNUM;
    }
    public void setICUSNUM(Long val) {
        ICUSNUM = val; 
    }
    @Column(name="DACTION")
    public LocalDate getDACTION() {
        return DACTION;
    }
    public void setDACTION(LocalDate val) {
        DACTION = val; 
    }
    @Column(name="DINFORM")
    public LocalDate getDINFORM() {
        return DINFORM;
    }
    public void setDINFORM(LocalDate val) {
        DINFORM = val; 
    }
    @Column(name="TINFORM",length = 1)
    public Long getTINFORM() {
        return TINFORM;
    }
    public void setTINFORM(Long val) {
        TINFORM = val; 
    }
    
    @Column(name="CCUSNUMNAL",length = 1)
    public String getCCUSNUMNAL() {
        return CCUSNUMNAL;
    }
    public void setCCUSNUMNAL(String val) {
        CCUSNUMNAL= val;
    }
    @Column(name="CCUSNAME_SH",length = 1)
    public String getCCUSNAME_SH() {
        return CCUSNAME_SH;
    }
    public void setCCUSNAME_SH(String val) {
        CCUSNAME_SH= val;
    }
    @Column(name="TINFDESCR",length = 1)
    public String getTINFDESCR() {
        return TINFDESCR;
    }
    public void setTINFDESCR(String val) {
        TINFDESCR= val;
    }
    
    @Transient
    @Override
    public Long getMarkLongID() {
        return getICKRAID();
    }
    @Override
    public boolean isMark() {
        return super.isMark();
    }
}