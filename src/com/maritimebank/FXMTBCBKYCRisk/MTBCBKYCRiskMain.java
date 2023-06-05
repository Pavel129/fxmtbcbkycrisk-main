package com.maritimebank.FXMTBCBKYCRisk;

import java.util.Collections;
import java.util.Map;
import ru.inversion.fx.form.ViewContext;
import ru.inversion.fx.app.BaseApp;
import ru.inversion.fx.form.FXFormLauncher;
import ru.inversion.tc.TaskContext;

/**
 *
 * @author  Grigorev
 * @since   Mon Apr 18 10:31:09 MSK 2022
 */
public class MTBCBKYCRiskMain extends BaseApp
{
    
    @Override
    protected void showMainWindow () 
    {
        showViewMtbcbKycRisk (getPrimaryViewContext (), new TaskContext (), Collections.emptyMap ());
    }

    @Override
    public String getAppID () 
    {
        return "XXI.MTBCBKYCRISKFILE";
    }
    
    public static void main (String[] args) 
    {
        launch (args);
    }

    public static void showViewMtbcbKycRisk(ViewContext vc, TaskContext tc, Map<String, Object> p)
    {
        new FXFormLauncher<> (tc, vc, ViewMtbcbKycRiskController.class)
            .initProperties (p)
            .show ();
    }
    
}

