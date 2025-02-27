/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.manager;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author KAILASH
 */
public class UI_DesignFunctions {
    
    // function to align table elements to center and render left
    public static void AlignTableContents(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.LEFT );
        for (int columnIndex = 0; columnIndex < table.getModel().getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(leftRenderer);
        }
        System.out.println("Table Alignment done");
    
    }
    
    public static void SetButtonBg(Color globalColor, JButton button){
        button.setBackground(globalColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }
    
    // Adding Button Colour to say its approved
    public static void SetButtonBgGreen(JButton button)
    {
        Color greencolour = new Color(114,255,123);
        button.setBackground(greencolour);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }
    
    public static void searchEmployeeDetails(String keyword,JTable table)
    {
        DefaultTableModel tableSearch = (DefaultTableModel)table.getModel();
        TableRowSorter<DefaultTableModel> sortertableSearch = new TableRowSorter<>(tableSearch);
        table.setRowSorter(sortertableSearch);
        sortertableSearch.setRowFilter(RowFilter.regexFilter("(?i)"+keyword));
    }
    
    // Adding Button Colour red to say its rejected
    public static void SetButtonBgRed(JButton button)
    {
        Color redColour = new Color(255,100,0);
        button.setBackground(redColour);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }
}
