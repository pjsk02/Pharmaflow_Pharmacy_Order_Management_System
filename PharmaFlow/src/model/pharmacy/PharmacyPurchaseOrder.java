/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.pharmacy;

import java.util.ArrayList;
import java.util.List;
import model.common.Date;
/**
 *
 * @author KAILASH
 */
public class PharmacyPurchaseOrder {
    private int orderId;
    private final int pharmacyId;
    private final int manufactureId;
    private final Date purchaseOrderDate;
    private String orderStatus;
    private final List<PharmacyPurchaseOrderItem> orderItems = new ArrayList<>();

    public PharmacyPurchaseOrder(int pharmacyId, int manufactureId, Date purchaseOrderDate) {
        this.pharmacyId = pharmacyId;
        this.manufactureId = manufactureId;
        this.purchaseOrderDate = purchaseOrderDate;
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
        
    public String getOrderStatus() {
        return orderStatus;
    }
    
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPharmacyId() {
        return pharmacyId;
    }

    public int getPharmacymanufactureId() {
        return manufactureId;
    }

    public Date getPurchaseOrderDate() {
        return purchaseOrderDate;
    }
    
    public List<PharmacyPurchaseOrderItem> getOrderItems() {
        return orderItems;
    }
}
