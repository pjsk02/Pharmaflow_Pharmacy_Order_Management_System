/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.common;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author priyankasatish
 */
public class Date {
    public int month;
    public int day;
    public int year;
    
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    
    public int calculateAgeInYears() {
        LocalDate birthDate = LocalDate.of(this.year, this.month, this.day);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
    
    public String getFormattedDate() {
        String seperator = "-";
        return this.year + seperator + this.month + seperator + this.day;
    }
    
}
