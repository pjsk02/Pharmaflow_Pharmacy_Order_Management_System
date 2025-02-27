/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.pharmacy;

import model.common.Drug;

/**
 *
 * @author KAILASH
 */
public class PharmacyPurchaseOrderItem {
    private final Drug drug;
    private final float selling_price;
    private final int quantity;

    public PharmacyPurchaseOrderItem(Drug drug, int quantity, float selling_price) {
        this.drug = drug;
        this.quantity = quantity;
        this.selling_price = selling_price;
    }

    public Drug getDrug() {
        return drug;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public float getSellingPrice() {
        return selling_price;
    }
}
