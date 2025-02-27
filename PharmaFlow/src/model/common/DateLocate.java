/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.common;

import java.time.LocalDate;

/**
 *
 * @author deepthiramesh
 */
public class DateLocate {
    public static Date DateLocate(LocalDate date) {
        int month = date.getDayOfMonth();
        int year = date.getYear();
        int day = date.getDayOfMonth();
        Date d = new Date(month, day, year);
        return d;
    }
}
