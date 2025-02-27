/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import model.pharmacy.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author priyankasatish
 */
public abstract class PharmacyManager {
    private final static String FILENAME = "PharmacyManager";
    public static java.sql.Connection con = Connection.getConnection();

    public static boolean createOrder(PharmacyPurchaseOrder order) throws Exception {
        boolean isCreated = true;
        int orderId = -1;
        try {
            try {
                order.setOrderStatus("placed");
                String queryToInsertOrder = "INSERT INTO pharmacy_order(order_date, manufacturer_id, pharmacy_id, order_status)"
                                + "values (?, ?, ?, ?)";
                PreparedStatement preparedStmt1 = con.prepareStatement(queryToInsertOrder);
                preparedStmt1.setString (1, order.getPurchaseOrderDate().getFormattedDate());
                preparedStmt1.setInt (2, order.getPharmacymanufactureId());
                preparedStmt1.setInt (3, order.getPharmacyId());
                preparedStmt1.setString (4, "PENDING");
                preparedStmt1.execute();
            } catch (SQLException e) {
                throw new Exception("Error inserting order: " + e);
            }
            
            try {
                String queryToGetOrderId = String.format("SELECT order_id FROM pharmacy_order WHERE order_id=LAST_INSERT_ID()");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(queryToGetOrderId);
                if (rs.next()) {
                    orderId = rs.getInt("order_id");
                } else {
                    throw new Error("Cannot find ID");
                }
            } catch (SQLException e) {
                throw new Exception("Error fetching order ID: " + e);
            }
            
            try {
                //Query to insert Order Items
                String queryToInsertOrderItems = "INSERT INTO pharmacy_order_item(item_id, quantity, order_id, cost_price)"
                                + "values (?, ?, ?, ?)";
                PreparedStatement preparedStmt2 = con.prepareStatement(queryToInsertOrderItems);
                for (PharmacyPurchaseOrderItem item : order.getOrderItems()) {
                    preparedStmt2.setInt (1, item.getDrug().getDrugId());
                    preparedStmt2.setInt (2, item.getQuantity());
                    preparedStmt2.setInt (3, orderId);
                    preparedStmt2.setFloat (4, item.getSellingPrice());
                    preparedStmt2.addBatch();
                }
                preparedStmt2.executeBatch();
            } catch (SQLException e) {
                throw new Exception("Error inserting order items: " + e);
            }
            return isCreated;
        } catch (SQLException e) {
            throw new Exception(FILENAME + "->" + "createOrder" + "->" + e);
        }
    }
    
    public static ResultSet fetchAllOrders(int pharmacyCompanyId) throws Exception {
        try {
            //Build Query
            String query = """
                SELECT po.order_id, po.order_date, po.manufacturer_id, c.company_name AS manufacturer_name, po.order_status, COUNT(poi.item_id) AS total_items, SUM(poi.cost_price*poi.quantity) AS total_price
                FROM pharmacy_order po
                JOIN company c ON c.company_id=po.manufacturer_id
                JOIN pharmacy_order_item poi ON poi.order_id = po.order_id
                JOIN master_drug_table md ON md.drug_id=poi.item_id
                WHERE po.pharmacy_id=%s
                GROUP BY po.order_id, po.order_date, po.order_status""";
            query = String.format(query, pharmacyCompanyId);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            throw new Exception(FILENAME + "->" + "fetchAllOrders" + "->" + e);
        } 
    }
    
    public static ResultSet fetchAllOrderItems(int orderId) throws Exception {
        try {
            //Build Query
            String query = """
                SELECT poi.item_id, md.drug_name, poi.quantity, poi.cost_price
                FROM pharmacy_order po
                JOIN company c ON c.company_id=po.manufacturer_id
                JOIN pharmacy_order_item poi ON poi.order_id = po.order_id
                JOIN master_drug_table md ON md.drug_id=poi.item_id
                WHERE po.order_id=%s""";
            query = String.format(query, orderId);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            throw new Exception(FILENAME + "->" + "fetchAllOrderItems" + "->" + e);
        } 
    }

    public static ResultSet displayManufacturerInventory() throws Exception {
        try {
            String query = """
                SELECT m.manufacturer_id, c.company_name AS manufacturer_name, d.drug_id, d.drug_name, m.quantity, m.selling_price
                FROM manufacturer_inventory m
                JOIN master_drug_table d on m.drug_id = d.drug_id
                JOIN company c on m.manufacturer_id = c.company_id""";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            throw new Exception(FILENAME + "->" + "displayManufacturerInventory" + "->" + e);
        } 
    }
    
    public static ResultSet fetchPharmacyInventory(int pharmacyId) throws Exception {
        try {
            String query = """
                SELECT p.inventory_id, p.drug_id, m.drug_name, p.quantity, p.cost_price, p.selling_price
                FROM pharmacy_inventory p
                JOIN master_drug_table m ON m.drug_id = p.drug_id
                JOIN company c ON p.pharmacy_id = c.company_id
                WHERE pharmacy_id=%s""";
            query = String.format(query, pharmacyId);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            throw new Exception(FILENAME + "->" + "fetchPharmacyInventory" + "->" + e);
        } 
    }

    public static boolean updateStockAndQuantity(PharmacyPurchaseOrder order) throws Exception {
        boolean isUpdated = false;
        try {
            for (PharmacyPurchaseOrderItem item : order.getOrderItems()) {
                //Check if item is present in the inventory
                String findStockQuery = "SELECT * FROM pharmacy_inventory WHERE pharmacy_id=%s AND drug_id=%s";
                findStockQuery = String.format(findStockQuery, order.getPharmacyId(), item.getDrug().getDrugId());
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(findStockQuery);
                
                //If not, add the item to inventory
                String queryToUpdateOrder;
                if(!rs.next()) {
                    queryToUpdateOrder = """
                        INSERT into pharmacy_inventory(pharmacy_id, drug_id, quantity)
                        VALUES (?, ?, ?)""";
                    PreparedStatement preparedStmt = con.prepareStatement(queryToUpdateOrder);
                    preparedStmt.setInt (1, order.getPharmacyId());
                    preparedStmt.setInt (2, item.getDrug().getDrugId());
                    preparedStmt.setInt (3, item.getQuantity());
                    preparedStmt.execute();
                }
                //Else, update the quantity of item
                else {
                    queryToUpdateOrder = """
                        UPDATE pharmacy_inventory
                        SET quantity=quantity+%s
                        WHERE drug_id=%s AND pharmacy_id=%s""";
                    queryToUpdateOrder = String.format(queryToUpdateOrder, item.getQuantity(), item.getDrug().getDrugId(), order.getPharmacyId());
                    PreparedStatement preparedStmt = con.prepareStatement(queryToUpdateOrder);
                    preparedStmt.execute();
                }
            }
            return !isUpdated;
        } catch (SQLException e) {
            throw new Exception(FILENAME + "->" + "updateStockQuantity" + "->" + e);           
        }
    }
    
    public static ResultSet fetchAllStores(int pharmacy_id) throws Exception {
        try {
            String queryToFetchStores = """
                SELECT store_id, store_name, store_address, store_zip, store_city
                FROM pharmacy_store
                WHERE pharmacy_id=%s""";
            queryToFetchStores = String.format(queryToFetchStores, pharmacy_id);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryToFetchStores);
            return rs;
        } catch (SQLException e) {
            throw new Exception(FILENAME + "->" + "fetchAllStores" + "->" + e);
        }
    }


    public static int deleteStore(int store_id) throws Exception {
        try {
            String queryToDeleteStore = "DELETE FROM pharmacy_store WHERE store_id=1";
            queryToDeleteStore = String.format(queryToDeleteStore, store_id);
            Statement stmt = con.createStatement();
            return stmt.executeUpdate(queryToDeleteStore);
        } catch (SQLException e) {
            throw new Exception(FILENAME + "->" + "deleteStore" + "->" + e);
        }
    }

    public static boolean addStore(PharmacyStore store) throws Exception {
        try {
            String queryToAddStore = "INSERT INTO pharmacy_store(pharmacy_id, store_name, store_address, store_zip, store_city)"
                    + "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(queryToAddStore);
            preparedStmt.setInt (1, store.getPharmacyId());
            preparedStmt.setString (2, store.getStoreName());
            preparedStmt.setString (3, store.location.address);
            preparedStmt.setString (4, store.location.zipcode);
            preparedStmt.setString (5, store.location.city);
            preparedStmt.execute();
            return true;
        } catch (SQLException e) {
            throw new Exception(FILENAME + "->" + "addStore" + "->" + e);
        }
    }
    
    public static ResultSet fetchAllStoreManagers(int pharmacyId) throws Exception {
        try {
            String queryToFetchStoreManagers = """
                SELECT person_name, person_gender, person_email, person_contact
                FROM person
                WHERE person_role="STORE_MANAGER" AND company_id=%s""";
            queryToFetchStoreManagers = String.format(queryToFetchStoreManagers, pharmacyId);
            Statement stmt = con.createStatement();
            return stmt.executeQuery(queryToFetchStoreManagers);
        } catch (SQLException e) {
            throw new Exception(FILENAME + "->" + "fetchAllStoreManagers" + "->" + e);
        }
    }
   
}
