/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import static database.Connection.con;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import model.common.Date;
import model.common.Drug;
import model.pharmacy.PharmacyPurchaseOrder;
import model.pharmacy.PharmacyPurchaseOrderItem;

/**
 *
 * @author deepthiramesh
 */
public class Common_Functions {
    public static PharmacyPurchaseOrder getOrderFromOrderId(int orderId) throws Exception {
        try {
            String queryToFetchOrder = """
                SELECT poi.item_id, md.drug_name, poi.quantity,  po.order_id, po.manufacturer_id, c.company_name as manufacturer_name, po.order_date, po.order_status, po.pharmacy_id
                FROM pharmacy_order po
                JOIN company c ON c.company_id=po.manufacturer_id
                JOIN pharmacy_order_item poi ON poi.order_id = po.order_id
                JOIN master_drug_table md ON md.drug_id=poi.item_id
                WHERE po.order_id=%s""";
            
            queryToFetchOrder = String.format(queryToFetchOrder, orderId);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryToFetchOrder);
            if (rs.next()) {
                int oId = rs.getInt("order_id");
                int pId = rs.getInt("pharmacy_id");
                int mId = rs.getInt("manufacturer_id");
                PharmacyPurchaseOrder order = new PharmacyPurchaseOrder(pId, mId, new Date(13, 22, 2020));
                List<PharmacyPurchaseOrderItem> orderItems = order.getOrderItems();
                order.setOrderId(oId);
                
                PharmacyPurchaseOrderItem firstItem = prepareOrderItem(rs);
                orderItems.add(firstItem);
                
                while(rs.next()) {
                    PharmacyPurchaseOrderItem item = prepareOrderItem(rs);
                    orderItems.add(item);
                }
                return order;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    
    private static PharmacyPurchaseOrderItem prepareOrderItem(ResultSet rs) throws Exception {
        int drugId = rs.getInt("item_id");
        String drugName = rs.getString("drug_name");
        int quantity = rs.getInt("quantity");
        Drug drug = new Drug(drugId, drugName);
        PharmacyPurchaseOrderItem item = new PharmacyPurchaseOrderItem(drug, quantity,0);
        return item;
    }
}
