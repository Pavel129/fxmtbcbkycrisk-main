package com.maritimebank.FXMTBCBKYCRisk;

import javafx.event.ActionEvent;
import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.fx.form.Alerts;
import ru.inversion.fx.form.JInvFXFormController;
import ru.inversion.fx.form.controls.*;
import javafx.fxml.FXML;
import ru.inversion.fxcus.cuslov.PCus;
import ru.inversion.fxcus.cuslov.PCusLOV;
import ru.inversion.meta.EntityMetadataFactory;
import ru.inversion.meta.IEntityMetaData;

/**
 * @author  Grigorev
 * @since   Thu Aug 25 19:03:27 MSK 2022
 */
public class EditMtbCbKycRiskActController extends JInvFXFormController <PMtbCbKycRiskAct>{
//
//
    @FXML JInvLongField ICKRAID;
      @FXML JInvLongField ICUSNUM;
      @FXML JInvTextField CCUSNAME;
    @FXML JInvCalendar DACTION;
    @FXML JInvCalendar DINFORM;
    @FXML JInvLongField TINFORM;
    @FXML JInvLabel lblICKRAID;
    //
// Initializes the controller class.
//
    @Override
    protected void init () throws Exception 
    {
        super.init ();
        ICKRAID.setEditable(false);
        ICKRAID.setFocusTraversable(false);
        
        if (getInitProperties().get("act_mode").equals("VM_INS")){
            setTitle(getBundleString("ADD.TITLE"));
            lblICKRAID.setVisible(false);
            ICKRAID.setVisible(false);
        }
        if (getInitProperties().get("act_mode").equals("VM_EDIT")){
            setTitle(getBundleString("EDIT.TITLE"));
            lblICKRAID.setVisible(true);
            ICKRAID.setVisible(true);
        }
    
        PCusLOV lov=new PCusLOV();
        lov.setWherePredicat("CCUSflag in (2,3,4)");
        //lov.setParameters((ParametersByName)p-> p.equals("flag") ? "1": null);
        lov.setChoiceOrderBy("iCUSnum desc");
        lov.bindCtrl(CCUSNAME, PCus::getCCUSNAME);
        ICUSNUM.setLOV(lov);
        
    }
    
    @Override
    protected boolean onOK ()
    {
        boolean ok = super.onOK ();
        
        if (ok && getInitProperties().get("act_mode")=="VM_INS"){
            ParamMap pm = new ParamMap();
            pm.put("picusnum", ICUSNUM.getValue());
            pm.put("pdaction", DACTION.getValue());
            pm.put("pdinform", DINFORM.getValue());
            pm.put("ptinform", TINFORM.getText());
            try {
                pm.exec(this,"new_act");
            } catch (Throwable ex) {
                handleException (ex);
            }
            if (pm.getLong("pid")<0){
                Alerts.error(this, getBundleString("NEW_ACT_ERR"));
            }else{
                getInitProperties().put("pid",pm.getLong("pid"));
            }
        }
        //
        if (ok && getInitProperties().get("act_mode")=="VM_EDIT"){
            ParamMap pm = new ParamMap();
            pm.put("picusnum", ICUSNUM.getValue());
            pm.put("pdaction", DACTION.getValue());
            pm.put("pdinform", DINFORM.getValue());
            pm.put("ptinform", TINFORM.getText());
            pm.put("pid", ICKRAID.getValue());
            try {
                pm.exec(this,"edit_act");
            } catch (Throwable ex) {
                handleException (ex);
            }
            if (pm.getLong("rid")<0){
                Alerts.error(this, getBundleString("EDIT_ACT_ERR"));
            }else{
                getInitProperties().put("pid",pm.getLong("rid"));
            }
        }
        
        return ok;
    }
    
}

