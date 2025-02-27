/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.Person.Person;

/**
 *
 * @author KAILASH
 */
public class Person_Manager {
    public static java.sql.Connection con = Connection.getConnection();
    public static boolean createUser(Person p, int companyId) throws Exception {
        boolean isInserted = true;
        try {
            String query = "INSERT INTO person(username, person_name, password, person_dob, person_gender, person_role, person_address, person_city, person_zipcode, company_id)"
                            + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, p.getUsername());
            preparedStmt.setString (2, p.getPersonName());
            preparedStmt.setString (3, p.getPassword());
            preparedStmt.setString (4, p.getPersonDob().getFormattedDate());
            preparedStmt.setString (5, p.getPersonGender());
            preparedStmt.setString (6, p.getPersonRole());
            preparedStmt.setString (7, p.getLocation().address);
            preparedStmt.setString (8, p.getLocation().city);
            preparedStmt.setString (9, p.getLocation().zipcode);
            preparedStmt.setInt (10, companyId);
            preparedStmt.execute();
            return isInserted;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public static ResultSet verifyUser(String username, char[] password, String role) throws Exception {
        boolean isValidUser = true;
        try {
            String queryToVerifyUser = """
                SELECT *
                FROM person
                WHERE username=\"%s\" AND person_role=\"%s\"""";
            queryToVerifyUser = String.format(queryToVerifyUser, username, role);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryToVerifyUser);
            System.out.println("in verif user at Person_Manager Class");
            return rs;
        } catch (SQLException e) {
            throw e;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
