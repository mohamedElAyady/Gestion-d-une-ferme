package com.example.mazraaty.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Con_infos {
    private String user = "root";
    private String password = "";
    private String uri = "jdbc:mysql://localhost:3306/mazraaty";
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(uri,user,password);
    }

}
