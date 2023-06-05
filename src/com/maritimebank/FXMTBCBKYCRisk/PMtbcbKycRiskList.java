package com.maritimebank.FXMTBCBKYCRisk;

import java.math.BigDecimal;
import java.time.*;
import java.io.Serializable;
import javax.persistence.*;
import ru.inversion.dataset.mark.*;

/**
 @author  Grigorev
 @since   2022/07/12 12:46:35
 */
@Entity (name="com.maritimebank.FXMTBCBKYCRisk.PMTBCBKYCRiskList")
@NamedNativeQuery (name="com.maritimebank.FXMTBCBKYCRisk.PMTBCBKYCRiskList", query="SELECT ICKRFID,INN,CLIENT_TYPE,RISK_LEVEL,RISK_DATE,MAINRISK,ADDRISK1,ADDRISK2,ADDRISK3,DESCR0,DESCR1,DESCR2,DESCR3,CROWID FROM mtb.cb_kyc_risk_list_fx")
@MarkColumns (columns = {"ROWID"})
public class PMTBCBKYCRiskList extends RowIDMarkable implements Serializable
{
    private static final long serialVersionUID = 12_07_2022_12_46_37L;
    
    private Long ICKRFID;
    private String INN;
    private String CLIENT_TYPE;
    private Long RISK_LEVEL;
    private LocalDate RISK_DATE;
    private BigDecimal MAINRISK;
    private BigDecimal ADDRISK1;
    private BigDecimal ADDRISK2;
    private BigDecimal ADDRISK3;
    private String DESCR0;
    private String DESCR1;
    private String DESCR2;
    private String DESCR3;
    private String CROWID;
    
    public PMTBCBKYCRiskList(){}
    
    @Id
    @Column(name="ICKRFID",nullable = false,length = 0)
    public Long getICKRFID() {
        return ICKRFID;
    }
    public void setICKRFID(Long val) {
        ICKRFID = val;
    }
    @Id
    @Column(name="INN",nullable = false,length = 12)
    public String getINN() {
        return INN;
    }
    public void setINN(String val) {
        INN = val;
    }
    @Column(name="CLIENT_TYPE",length = 8)
    public String getCLIENT_TYPE() {
        return CLIENT_TYPE;
    }
    public void setCLIENT_TYPE(String val) {
        CLIENT_TYPE = val;
    }
    @Column(name="RISK_LEVEL",length = 1)
    public Long getRISK_LEVEL() {
        return RISK_LEVEL;
    }
    public void setRISK_LEVEL(Long val) {
        RISK_LEVEL = val;
    }
    @Column(name="RISK_DATE")
    public LocalDate getRISK_DATE() {
        return RISK_DATE;
    }
    public void setRISK_DATE(LocalDate val) {
        RISK_DATE = val;
    }
    @Column(name="MAINRISK",length = 6)
    public BigDecimal getMAINRISK() {
        return MAINRISK;
    }
    public void setMAINRISK(BigDecimal val) {
        MAINRISK = val;
    }
    @Column(name="ADDRISK1",length = 6)
    public BigDecimal getADDRISK1() {
        return ADDRISK1;
    }
    public void setADDRISK1(BigDecimal val) {
        ADDRISK1 = val;
    }
    @Column(name="ADDRISK2",length = 6)
    public BigDecimal getADDRISK2() {
        return ADDRISK2;
    }
    public void setADDRISK2(BigDecimal val) {
        ADDRISK2 = val;
    }
    @Column(name="ADDRISK3",length = 6)
    public BigDecimal getADDRISK3() {
        return ADDRISK3;
    }
    public void setADDRISK3(BigDecimal val) {
        ADDRISK3 = val;
    }
    @Column(name="DESCR0",length = 2000)
    public String getDESCR0() {
        return DESCR0;
    }
    public void setDESCR0(String val) {
        DESCR0 = val;
    }
    @Column(name="DESCR1",length = 2000)
    public String getDESCR1() {
        return DESCR1;
    }
    public void setDESCR1(String val) {
        DESCR1 = val;
    }
    @Column(name="DESCR2",length = 2000)
    public String getDESCR2() {
        return DESCR2;
    }
    public void setDESCR2(String val) {
        DESCR2 = val;
    }
    @Column(name="DESCR3",length = 2000)
    public String getDESCR3() {
        return DESCR3;
    }
    public void setDESCR3(String val) {
        DESCR3 = val;
    }
    
    @Column(name="CROWID")
    public String getROWID() {
        return CROWID;
    }
    public void setROWID(String val) {
        CROWID = val;
    }
    
    @Transient
    @Override
    public String getMarkRowID() {
        return getROWID();
    }
    @Override
    public boolean isMark() {
        return super.isMark();
    }
}