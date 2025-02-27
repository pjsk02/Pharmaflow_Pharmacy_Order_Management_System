/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.manager;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import ui.distributor.DistributorManagerJPanel;
import ui.login.CompanyRegisterationPanel;
import ui.login.LoginPageJPanel;
import ui.manufacturer.ManufacturerAdminJPanel;
import ui.pharmacy.PharmacyAdministratorPanel;
import ui.pharmacy.PharmacyStoreManagerPanel;
import ui.transporter.TransportAdminPanel;
import ui.account.AccountingmanagerJPanel;
import ui.distributor.DistributorAdminJPanel;
import ui.sales.SalesManagerJPanel;
/**
 *
 * @author KAILASH
 */
public class UI_Manager {
    private final static MainJFrame frame = new MainJFrame();
    private static LoginPageJPanel loginPageJPanel;
    private static ManufacturerAdminJPanel map;
    private static AccountingmanagerJPanel am;
    private static PharmacyAdministratorPanel pam;
    private static CompanyRegisterationPanel crp;
    private static DistributorManagerJPanel dmp;
    private static TransportAdminPanel tap;
    private static PharmacyStoreManagerPanel psm;
    private static SalesManagerJPanel sm;
    private static DistributorAdminJPanel dap;
    
    private static void removeAndAddPanel(JPanel panel) {
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.repaint();
        frame.revalidate();
    }
    
    public static void init(){
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        loginPageJPanel = new LoginPageJPanel();
        removeAndAddPanel(loginPageJPanel);
    }
    
    public static void AddTransportAdminPanel(String user) 
        {
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        tap = new TransportAdminPanel(user);
        removeAndAddPanel(tap);
    }
    public static void AddManuAdminPanel(String username, int manId) 
        {
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        map = new ManufacturerAdminJPanel(username, manId);
        removeAndAddPanel(map);
    }
    
    public static void AddpharmacyAdminPanel(String username, int pharmacyId) {
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        pam = new PharmacyAdministratorPanel(username, pharmacyId);
        removeAndAddPanel(pam);
    }
    
    public static void AddPharmacyStoreManager(String username){
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        psm = new PharmacyStoreManagerPanel(username);
        removeAndAddPanel(psm);  
      }
    
    public static void AddDistributorManagerPanel(String username, int distributorId){
      frame.setLayout(new FlowLayout());
      frame.setVisible(true);
      dmp = new DistributorManagerJPanel(username, distributorId);
      removeAndAddPanel(dmp);  
    }
    
    public static void AddDistributorAdminPanel(String username, int distributorId){
      frame.setLayout(new FlowLayout());
      frame.setVisible(true);
      dap = new DistributorAdminJPanel(username, distributorId);
      removeAndAddPanel(dap);  
    }
    
    public static void AddAccountingManager(String username){
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        am = new AccountingmanagerJPanel(username);
        removeAndAddPanel(am);  
      }
    
    public static void AddSalesManager(String username){
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        sm = new SalesManagerJPanel(username);
        removeAndAddPanel(sm);  
      }
    
    
    public static void AddCompanyRegPanel(String companyType){
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
        crp = new CompanyRegisterationPanel(companyType);
        removeAndAddPanel(crp);  
      }
    
}
