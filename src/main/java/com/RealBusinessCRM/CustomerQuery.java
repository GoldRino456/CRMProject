package com.RealBusinessCRM;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class CustomerQuery {

    /** Queries the Database to retrieve the full Customer List
     *
     * <p>Selects all customers from the customer table and returns them as a ObservableList of
     * Customer objects.</p>
     *
     *
     */
    public static ObservableList<Customer> selectAll() throws SQLException {
        ObservableList<Customer> customers = FXCollections.observableList(new ArrayList<Customer>());

        //SQL Query
        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        //Pull data from Query
        while(rs.next()) {
            //Create new customer object for customer in table
            Customer temp = new Customer(
                    rs.getInt("Customer_ID"),
                    rs.getString("Customer_Name"),
                    rs.getString("Address"),
                    rs.getString("Postal_Code"),
                    rs.getString("Phone"),
                    rs.getInt("Division_ID"));

            customers.add(temp);
        }

        return customers;
    }
}
