package com.maritimebank.FXMTBCBKYCRisk;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Separator;
import javafx.stage.FileChooser;
import ru.inversion.bicomp.action.JInvButtonPrint;
import ru.inversion.bicomp.action.StopExecuteActionBiCompException;
import ru.inversion.bicomp.fxreport.ApReport;
import ru.inversion.bicomp.pref.PreferencesWorker;
import ru.inversion.bicomp.util.ParamMap;
import ru.inversion.dataset.DataLinkBuilder;
import ru.inversion.dataset.IDataSet;
import ru.inversion.dataset.XXIDataSet;
import ru.inversion.dataset.aggr.AggrFuncEnum;
import ru.inversion.dataset.fx.DSFXAdapter;
import ru.inversion.fx.form.action.ActionBuilder;

import ru.inversion.fx.form.*;
import ru.inversion.fx.form.controls.*;
import ru.inversion.fx.form.controls.dsbar.DSInfoBar;
import ru.inversion.fx.form.controls.table.toolbar.AggregatorType;
import ru.inversion.fxpdoc.kostrep.KostRepUtil;
import ru.inversion.icons.IconDescriptorBuilder;
import ru.inversion.icons.enums.FontAwesome;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author  Grigorev
 * @since   Mon Apr 18 10:31:04 MSK 2022
 */
public class ViewMtbcbKycRiskController extends JInvFXBrowserController
{
    @FXML private JInvTable<PMtbcbKycRiskFile>  MTB_CB_KYC_RISK_FILE;
    @FXML private JInvTable<PMTBCBKYCRiskList>  MTB_CB_KYC_RISK_LIST;
    @FXML private JInvTable<PMtbCbKycRiskAct>   MTB_CB_KYC_RISK_ACT;
    @FXML private JInvToolBar toolBar,toolBarAct;
    
    @FXML private JInvTextField DESCR0,DESCR1,DESCR2,DESCR3;
    
    @FXML private DSInfoBar MTB_CB_KYC_RISK_ACT$MARK;
    
    private final XXIDataSet<PMtbcbKycRiskFile> dsMTB_CB_KYC_RISK_FILE = new XXIDataSet<> ();
    private final XXIDataSet<PMTBCBKYCRiskList> dsMTB_CB_KYC_RISK_LIST = new XXIDataSet<> ();
    private final XXIDataSet<PMtbCbKycRiskAct>  dsMTB_CB_KYC_RISK_ACT  = new XXIDataSet<> ();
    
    private POptions pv = new POptions ();
    private String act_mode;
    
    ButtonType btn_do,btn_cancel;
    
    Logger logger= Logger.getLogger(ViewMtbcbKycRiskController.class.getName());
    
//
    private void initDataSet () throws Exception 
    {
        dsMTB_CB_KYC_RISK_FILE.setTaskContext (getTaskContext ());
        dsMTB_CB_KYC_RISK_FILE.setRowClass (PMtbcbKycRiskFile.class);
        dsMTB_CB_KYC_RISK_FILE.setOrderBy("ICKRFID DESC");
    
        dsMTB_CB_KYC_RISK_LIST.setTaskContext (getTaskContext ());
        dsMTB_CB_KYC_RISK_LIST.setRowClass (PMTBCBKYCRiskList.class);
        dsMTB_CB_KYC_RISK_LIST.setOrderBy("ICKRFID DESC, INN ASC");
    
        dsMTB_CB_KYC_RISK_ACT.setTaskContext (getTaskContext ());
        dsMTB_CB_KYC_RISK_ACT.setRowClass (PMtbCbKycRiskAct.class);
        
        MTB_CB_KYC_RISK_LIST.setSaveMark(true);
    
        DataLinkBuilder.linkDataSet(dsMTB_CB_KYC_RISK_FILE,dsMTB_CB_KYC_RISK_LIST,PMtbcbKycRiskFile::getICKRFID,"ICKRFID");
    
    }
//
// Initializes the controller class.
//
    @Override
    protected void init() throws Exception
    {
        setTitle (getBundleString ("VIEW.TITLE"));
        PreferencesWorker.load (getTaskContext (), pv);
        if (!pv.isAction (-15)) throw new SecurityException (pv.getActionError (getTaskContext (), -15));
    
        btn_do       = new ButtonType(getBundleString("BTN_DO"), ButtonBar.ButtonData.OK_DONE);
        btn_cancel   = new ButtonType(getBundleString("BTN_CANCEL"), ButtonBar.ButtonData.CANCEL_CLOSE);
        
        initDataSet ();
        DSFXAdapter<PMtbcbKycRiskFile> dsfxFile = DSFXAdapter.bind (dsMTB_CB_KYC_RISK_FILE, MTB_CB_KYC_RISK_FILE, null, false);
        DSFXAdapter<PMTBCBKYCRiskList> dsfxList = DSFXAdapter.bind (dsMTB_CB_KYC_RISK_LIST, MTB_CB_KYC_RISK_LIST, null, true);
        DSFXAdapter<PMtbCbKycRiskAct>  dsfxAct  = DSFXAdapter.bind (dsMTB_CB_KYC_RISK_ACT, MTB_CB_KYC_RISK_ACT, null, true);
        
        dsfxFile.setEnableFilter (true,"MTB.CB_KYC_RISK_FILE","MTB_CB_KYC_RISK_FILE");
        dsfxList.setEnableFilter (true,"MTB.CB_KYC_RISK_LIST","MTB_CB_KYC_RISK_LIST");
        dsfxAct.setEnableFilter (true);
        MTB_CB_KYC_RISK_ACT$MARK.init (MTB_CB_KYC_RISK_ACT.getDataSetAdapter ());
        MTB_CB_KYC_RISK_ACT$MARK.addAggregator ("1", AggrFuncEnum.COUNT, AggregatorType.MARK, null, null);
        
        initToolBar ();

        MTB_CB_KYC_RISK_FILE.setToolBar (toolBar);       
        MTB_CB_KYC_RISK_FILE.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh (MTB_CB_KYC_RISK_FILE));
    
        MTB_CB_KYC_RISK_LIST.setToolBar (toolBar);
        MTB_CB_KYC_RISK_LIST.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh (MTB_CB_KYC_RISK_LIST));
    
        MTB_CB_KYC_RISK_ACT.setToolBar (toolBarAct);
        MTB_CB_KYC_RISK_ACT.setAction (ActionFactory.ActionTypeEnum.CREATE, (a) -> doOperation (FormModeEnum.VM_INS));
        MTB_CB_KYC_RISK_ACT.setAction (ActionFactory.ActionTypeEnum.UPDATE, (a) -> doOperation (FormModeEnum.VM_EDIT));
        MTB_CB_KYC_RISK_ACT.setAction (ActionFactory.ActionTypeEnum.DELETE, (a) -> doOperation (FormModeEnum.VM_DEL));
        MTB_CB_KYC_RISK_ACT.setAction (ActionFactory.ActionTypeEnum.REFRESH, (a) -> doRefresh (MTB_CB_KYC_RISK_ACT));
        
        dsfxList.bindControl(DESCR0,DESCR1,DESCR2,DESCR3);
        
        doRefresh (MTB_CB_KYC_RISK_FILE);
        doRefresh(MTB_CB_KYC_RISK_ACT);
        
    }        
//
//
//    
    private void doRefresh (JInvTable<?> t) {
        if (t.getId().equals("MTB_CB_KYC_RISK_ACT")){
            long currID = (dsMTB_CB_KYC_RISK_ACT.isEmpty()) ? 0 : dsMTB_CB_KYC_RISK_ACT.getCurrentRow().getICKRAID();
            t.executeQuery ((ok, th)-> {
                if (ok)
                    if (!(currID == 0)) {
                        dsMTB_CB_KYC_RISK_ACT.findRow((dataSet, someValue) -> dataSet.getICKRAID().equals(someValue), currID);
                    } else
                        handleException(th);
            }, this);
        }else{
            t.executeQuery();
        }
    }
//
//
//    
    private void initToolBar () 
    {
        toolBar.setStandartActions (ActionFactory.ActionTypeEnum.REFRESH);
        
        if (pv.isAction (-16)) {
            toolBar.getItems().addAll(
                    new Separator(Orientation.VERTICAL)
                    ,ActionFactory.createButton(
                            new ActionBuilder()
                                    .icon ( new IconDescriptorBuilder<>().iconId(FontAwesome.fa_close).build() )
                                    //.setActionType(ActionFactory.ActionTypeEnum.DELETE)
                                    .handler((a)->doDeleteFile())
                                    .toolTipText( getBundleString("ACTION_FILE_DELETE"))
                                    .build())
                    ,ActionFactory.createButton(
                            new ActionBuilder()
                                    .icon ( new IconDescriptorBuilder<>().iconId(FontAwesome.fa_file_o).build() )
                                    //.setActionType(ActionFactory.ActionTypeEnum.OPEN_FILE)
                                    .handler((a)->doLoadFile())
                                    .toolTipText( getBundleString("ACTION_FILE_LOAD"))
                                    .build())
                    );
        }
        toolBar.getItems().addAll(
                new Separator(Orientation.VERTICAL)
                ,new JInvButtonPrint(this::setPrintParam)
                );
        
        toolBarAct.setStandartActions (ActionFactory.ActionTypeEnum.CREATE,
                ActionFactory.ActionTypeEnum.UPDATE,
                ActionFactory.ActionTypeEnum.DELETE,
                ActionFactory.ActionTypeEnum.REFRESH);
        if (pv.isAction (-16)) {
            toolBarAct.getItems().addAll(
                    new Separator(Orientation.VERTICAL)
                    ,ActionFactory.createButton(
                            new ActionBuilder()
                                    .icon ( new IconDescriptorBuilder<>().iconId(FontAwesome.fa_floppy_o).build() )
                                    //.setActionType(ActionFactory.ActionTypeEnum.DELETE)
                                    .handler((a)->doExportFile())
                                    .toolTipText( getBundleString("ACTION_FILE_EXPORT"))
                                    .build())
            );
        }
//        toolBar.getItems ().add (ActionFactory.createButton(ActionFactory.ActionTypeEnum.SETTINGS, (a) -> JInvMainFrame.showSettingsPane ()));
    }
    
    private void doExportFile(){
        if(dsMTB_CB_KYC_RISK_ACT.getMarkerID()==null
        ||MTB_CB_KYC_RISK_ACT$MARK.getAggregatorValue (AggregatorType.MARK, "1")==null
        ||(Long)MTB_CB_KYC_RISK_ACT$MARK.getAggregatorValue (AggregatorType.MARK, "1")<1L
        ){
            Alerts.error(null,getBundleString("EMPTY_MARK"));
        }else{
            ParamMap pm=new ParamMap();
            pm.put("pMarkerId",dsMTB_CB_KYC_RISK_ACT.getMarkerID());
            try {
                pm.exec(this, "export_act");
            } catch (Throwable ex) {
                handleException(ex);
            }
            Clob report=pm.getParam("pReportXml");
            if (report!=null){
                String strFileName= "KYCHR_7714060199_0077_"
                        .concat((new SimpleDateFormat("yyyyMMdd")).format(new Date()))
                        .concat("_000001.xml");
                FileChooser fileChooser=new FileChooser();
                fileChooser.setInitialFileName(strFileName);
                fileChooser.setInitialDirectory(new File(pv.getCDIROUTPBOX()));
                fileChooser.setTitle(getBundleString("SCR_FILE.TITLE"));
                FileChooser.ExtensionFilter fltXML=new FileChooser.ExtensionFilter("KYCHR (*.xml)", "KYCHR_*.xml");
                fileChooser.getExtensionFilters().addAll(fltXML);
                fileChooser.setSelectedExtensionFilter(fltXML);
                File vScrFile = fileChooser.showSaveDialog(this.getViewContext().getStage());
                
                if (vScrFile!=null)
                try {
                    Reader reader = null;
                    try {
                        reader = new BufferedReader(report.getCharacterStream());
                        int length;
                        char[] buf = new char[32768];
                        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(vScrFile), StandardCharsets.UTF_8))) {
                            while ((length = reader.read(buf)) != -1) {
                                writer.write(buf, 0, length);
                            }
                        } catch (Throwable ex) {
                            handleException(ex);
                        }
                    } finally {
                        if (reader != null) reader.close();
                    }
                    if(vScrFile.exists()&&vScrFile.length()>0){
                        Alerts.info(null,getBundleString("FILE_READY"));
                        dsMTB_CB_KYC_RISK_ACT.clearMark();
                        doRefresh(MTB_CB_KYC_RISK_ACT);
                    }
                }catch (Exception e){
                    handleException(e);
                }
                
            }
        }
    }
    
    private void doLoadFile(){
        //
        String def_folder="C:\\";
        String def_folder_arh="C:\\";
        
        ParamMap pm = new ParamMap();
        try {
            pm.exec(this, "get_in_folder");
            if(pm.getString("in_folder")!=null)
                if(!pm.getString("in_folder").isEmpty())
                    def_folder=pm.getString("in_folder");
            //if(pm.getString("in_folder_arh")!=null)
            //    if(!pm.getString("in_folder_arh").isEmpty())
                    def_folder_arh=pm.getString("in_folder_arh");
        }catch  (Throwable ex) {
            handleException (ex);
        }
        
        File vCat=new File(def_folder);//System.getenv("TEMP"));
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(vCat);
        fileChooser.setTitle("Выбор файла");
        FileChooser.ExtensionFilter flt1=new FileChooser.ExtensionFilter("Реестр KYC Risk", "2ndp*.xml");
        FileChooser.ExtensionFilter flt2=new FileChooser.ExtensionFilter("All Files (*.*)", "*.*");
        fileChooser.getExtensionFilters().addAll(flt1,flt2);
        fileChooser.setSelectedExtensionFilter(flt1);
        File vFile = fileChooser.showOpenDialog(this.getViewContext().getStage());
        if (super.onOK()) {
            if (vFile!=null){
                if (vFile.exists()){
                    String loadFileName = vFile.getName();
                    String loadFileCat = vFile.getParent();
                    //ParamMap pm = new ParamMap();
                    pm.put("filename",  loadFileName);
                    pm.put("filebody",  vFile);
                    try {
                        pm.exec(this,"file_load");
                    } catch (Throwable ex) {
                        handleException (ex);
                    }
                    if (pm.getLong("pID")<0){
                        Alerts.error(this, getBundleString("FILE_LOAD_ERR").concat(":\n").concat(pm.getString("cerrmsg")));
                    }else{
                        // move file
                        moveFile(loadFileCat,def_folder_arh,loadFileName);
                        doRefresh(MTB_CB_KYC_RISK_FILE);
                    }
                } else {
                    Alerts.error(this, getBundleString("FILE_NOT_FOUND"));
                }
            }
        }
    }
    public void moveFile(String pInPath, String pOutPath, String pFName) {
        if (pOutPath!=null) {
            try{
                File justfile=new File(pInPath+"\\"+pFName);
                //
                if(justfile.renameTo(new File(pOutPath+'\\'+ justfile.getName()))){
                    logger.log(Level.INFO, null,getBundleString("FILE_MOVE_INF")+" "+pOutPath+"\\"+pFName);
                }else{
                    Alerts.error(this, getBundleString("FILE_MOVE_ERR")+" "+pOutPath);
                    logger.log(Level.SEVERE, null,getBundleString("FILE_MOVE_ERR")+" "+pOutPath);
                }
                //
            }catch(Exception e){
                handleException (e);
            }
        }
    }
    
    private void doDeleteFile(){
        ParamMap pm = new ParamMap();
        pm.put("pID", dsMTB_CB_KYC_RISK_FILE.getCurrentRow().getICKRFID());
        try {
            pm.exec(this,"file_delete");
        } catch (Throwable ex) {
            handleException (ex);
        }
        if (pm.getLong("ret")<0){
            Alerts.error(this, getBundleString("FILE_DELETE_ERR").concat(":\n").concat(pm.getString("cerrmsg")));
        }
        doRefresh(MTB_CB_KYC_RISK_FILE);
    }
    
    private void setPrintParam(ApReport ap) {
        if (dsMTB_CB_KYC_RISK_FILE.isEmpty()||dsMTB_CB_KYC_RISK_LIST.isEmpty())
            throw new StopExecuteActionBiCompException(getBundleString("EMPTY_RECORDSET"));
        ap.setTypeID(-3L);
        ap.setParam(
                 dsMTB_CB_KYC_RISK_FILE.getCurrentRow().getICKRFID().toString()
                ,dsMTB_CB_KYC_RISK_LIST.getCurrentRow().getINN()
                ,dsMTB_CB_KYC_RISK_LIST.getMarkerID().toString()
        );
    }
    
    private void doOperation ( JInvFXFormController.FormModeEnum mode )
    {
        if (pv.isAction (-16)) {
            PMtbCbKycRiskAct p = null;
        
            switch (mode) {
                case VM_INS:
                    p = new PMtbCbKycRiskAct();
                    act_mode = "VM_INS";
                    break;
                case VM_EDIT:
                    p = dsMTB_CB_KYC_RISK_ACT.getCurrentRow();
                    act_mode = "VM_EDIT";
                    break;
                case VM_DEL:
                    act_mode = "VM_DEL";
                    break;
            }
            if (!act_mode.equals("VM_DEL")) {
                if (p != null) {
                    Map<String, Object> pm = new HashMap<>();
                    pm.put("act_mode", act_mode);
                    new FXFormLauncher<PMtbCbKycRiskAct>(getTaskContext(), getViewContext(), EditMtbCbKycRiskActController.class)
                            .dataObject(p)
                            //.dialogMode (FormModeEnum.VM_SHOW)
                            .initProperties(pm)
                            .callback(this::doFormResult)
                            .modal(true)
                            .show();
                }
            } else {
                Alert user_alert = new Alert(Alert.AlertType.WARNING, null, btn_do, btn_cancel);
                user_alert.setTitle(getBundleString("TITLE_WARNING"));
                user_alert.setHeaderText(getBundleString("ACT_DELETE_WARN").concat("\n").concat(dsMTB_CB_KYC_RISK_ACT.getCurrentRow().getCCUSNAME_SH()));
                user_alert.showAndWait();
                //
                if (user_alert.getResult() != null) {
                    if (user_alert.getResult().getText().equalsIgnoreCase(getBundleString("BTN_DO"))) {
                        ParamMap pm = new ParamMap();
                        pm.put("pid", dsMTB_CB_KYC_RISK_ACT.getCurrentRow().getICKRAID());
                        try {
                            pm.exec(this, "del_act");
                        } catch (Throwable ex) {
                            handleException(ex);
                        }
                        doRefresh(MTB_CB_KYC_RISK_ACT);
                    }
                }
            }
        }else{
            Alerts.info(null,getBundleString("ACCESS_EDIT"));
        }
    }
    //
//
//
    private void doFormResult ( JInvFXFormController.FormReturnEnum ok, JInvFXFormController<PMtbCbKycRiskAct> dctl )
    {
        if (JInvFXFormController.FormReturnEnum.RET_OK == ok){
            switch (act_mode){
                case "VM_EDIT":
                case "VM_INS":
                    doRefresh(MTB_CB_KYC_RISK_ACT);
                    dsMTB_CB_KYC_RISK_ACT.findRow((dataSet, someValue) -> dataSet.getICKRAID().equals(someValue), dctl.getInitProperties().get("pid"));
                    break;
                default:break;
            }
        }
        
        MTB_CB_KYC_RISK_ACT.requestFocus ();
    }
//
}

