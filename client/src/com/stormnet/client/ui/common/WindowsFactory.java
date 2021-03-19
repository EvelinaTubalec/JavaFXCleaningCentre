package com.stormnet.client.ui.common;

import com.stormnet.client.ui.allOrders.AllOrdersWindow;
import com.stormnet.client.ui.authentication.manager.LoginManagerWindow;
import com.stormnet.client.ui.authentication.client.LoginClientWindow;
import com.stormnet.client.ui.authentication.client.details.AuthClientWindow;
import com.stormnet.client.ui.clientaccount.ClientAccountWindow;
import com.stormnet.client.ui.clinservices.service1.Service1Window;
import com.stormnet.client.ui.clinservices.service1.details.AddService1Window;
import com.stormnet.client.ui.clinservices.service2.Service2Window;
import com.stormnet.client.ui.clinservices.service2.details.AddService2Window;
import com.stormnet.client.ui.clinservices.service3.Service3Window;
import com.stormnet.client.ui.clinservices.service3.details.AddService3Window;
import com.stormnet.client.ui.clinservices.service4.Service4Window;
import com.stormnet.client.ui.clinservices.service4.details.AddService4Window;
import com.stormnet.client.ui.clinservices.service5.Service5Window;
import com.stormnet.client.ui.clinservices.service5.details.AddService5Window;
import com.stormnet.client.ui.history.HistoryOfOrdersWindow;
import com.stormnet.client.ui.manager.ManagerCabinetWindow;
import com.stormnet.client.ui.pricelist.PriceListWindow;
import com.stormnet.client.ui.recording.RecordNewOrderWindow;
import com.stormnet.client.ui.registration.ClientRegisterWindow;
import com.stormnet.client.ui.review.GiveReviewWindow;
import com.stormnet.client.ui.review.reviews.ShowAllReviewsWindow;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Map;

public class WindowsFactory {

    public static final WindowsFactory factory = new WindowsFactory();

    private Stage mainWindow;

    private Map<String,BaseWindow> allWindows = new HashMap<>();

    private WindowsFactory() {
        try {
            LoginManagerWindow loginManagerWindow = new LoginManagerWindow();
            LoginClientWindow loginClientWindow = new LoginClientWindow();
            ClientRegisterWindow clientRegisterWindow = new ClientRegisterWindow();
            AllOrdersWindow allOrdersWindow = new AllOrdersWindow();
            RecordNewOrderWindow recordNewOrderWindow = new RecordNewOrderWindow();
            PriceListWindow priceListWindow = new PriceListWindow();
            Service1Window service1Window = new Service1Window();
            Service2Window service2Window = new Service2Window();
            Service3Window service3Window = new Service3Window();
            Service4Window service4Window = new Service4Window();
            Service5Window service5Window = new Service5Window();
            AddService1Window addService1Window = new AddService1Window();
            AddService2Window addService2Window = new AddService2Window();
            AddService3Window addService3Window = new AddService3Window();
            AddService4Window addService4Window = new AddService4Window();
            AddService5Window addService5Window = new AddService5Window();
            HistoryOfOrdersWindow historyOfOrdersWindow = new HistoryOfOrdersWindow();
            AuthClientWindow authClientWindow = new AuthClientWindow();
            ClientAccountWindow clientAccountWindow = new ClientAccountWindow();
            GiveReviewWindow giveReviewWindow = new GiveReviewWindow();
            ShowAllReviewsWindow showAllReviewsWindow = new ShowAllReviewsWindow();
            ManagerCabinetWindow managerCabinetWindow = new ManagerCabinetWindow();

            allWindows.put("loginAdminWindow", loginManagerWindow);
            allWindows.put("loginClientWindow", loginClientWindow);
            allWindows.put("authClientWindow", authClientWindow);
            allWindows.put("registerWindow", clientRegisterWindow);
            allWindows.put("allOrdersWindow", allOrdersWindow);
            allWindows.put("recordingWindow", recordNewOrderWindow);
            allWindows.put("priceListWindow",priceListWindow);
            allWindows.put("service1Window",service1Window);
            allWindows.put("service2Window",service2Window);
            allWindows.put("service3Window",service3Window);
            allWindows.put("service4Window",service4Window);
            allWindows.put("service5Window",service5Window);
            allWindows.put("addService1Window",addService1Window);
            allWindows.put("addService2Window",addService2Window);
            allWindows.put("addService3Window",addService3Window);
            allWindows.put("addService4Window",addService4Window);
            allWindows.put("addService5Window",addService5Window);
            allWindows.put("historyWindow", historyOfOrdersWindow);
            allWindows.put("clientAccountWindow", clientAccountWindow);
            allWindows.put("giveReviewWindow", giveReviewWindow);
            allWindows.put("showAllReviewsWindow", showAllReviewsWindow);
            allWindows.put("managerCabinetWindow", managerCabinetWindow);
        }catch(Exception e){
            throw new CreateWindowException(e);
        }
    }

    public static WindowsFactory getWindowFactory(){
        return factory;
    }

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void showMainWindow() {
        mainWindow.show();
    }

    public void hideMainWindow() {
        mainWindow.hide();
    }

    public void showWindow(String windowName) {
       Stage stage = allWindows.get(windowName);
        if(stage != null) {
            stage.show();
        }
    }

    public void hideWindow(String windowName) {
        Stage stage = allWindows.get(windowName);
        if(stage != null) {
            stage.hide();
        }
    }

    public Object getWindowController(String windowName){
        BaseWindow baseWindow = allWindows.get(windowName);
        Object controller = baseWindow.getController();
        return controller;
    }
}
