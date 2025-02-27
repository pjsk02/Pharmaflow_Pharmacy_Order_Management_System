/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.pharmacy.PharmacyPurchaseOrder;

/**
 *
 * @author priyankasatish
 */
public class TransportManager {
    public static java.sql.Connection con = Connection.getConnection();
    public static boolean confirmShipmentStatus(int orderId) throws Exception {
        boolean isShipped = false;
        try {
            String queryToUpdateStatus = """
                UPDATE pharmacy_order
                SET order_status=\"DELIVERED\"
                WHERE order_id=%s""";
            queryToUpdateStatus = String.format(queryToUpdateStatus, orderId);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(queryToUpdateStatus);
            
            PharmacyPurchaseOrder order = Common_Functions.getOrderFromOrderId(orderId);
            PharmacyManager.updateStockAndQuantity(order);
            return !isShipped;
        } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Invalid Order ID. Please check the value and try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
        return false;
    }
    public static ResultSet fetchAllShipments(int transporterId) throws Exception {
        try {
            String queryToFetchShipments = """
                SELECT s.shipment_id, po.order_date, po.pharmacy_id,c1.company_name,c2.company_name,s.shipment_status FROM shipment s JOIN pharmacy_order po ON s.order_id = po.order_id Join company c1 ON po.pharmacy_id = c1.company_id JOIN company c2 ON po.distributor_id = c2.company_id;""";
            queryToFetchShipments = String.format(queryToFetchShipments, transporterId);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryToFetchShipments);
            return rs;
        } catch (SQLException e) {
            throw e;
        }
    }

    
}
