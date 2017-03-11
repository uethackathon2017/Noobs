package com.noobs.model;
import java.sql.*;
import java.util.ArrayList;

public class SQLite
{
    private Connection c;
    private Statement stmt;

    public SQLite()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            stmt = c.createStatement();
            // String sql = "CREATE TABLE BIGBOARD (NAME CHAR(100) NOT NULL)";
            // stmt.executeUpdate(sql);
        }  
        catch (Exception e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Opened database successfully");
    }

    public void write(MonHoc monhoc)
    {
        try
        {
            ResultSet rs = stmt.executeQuery("SELECT * FROM BIGBOARD WHERE NAME='" + monhoc.getTenMonHoc() + "'");
            if (!rs.next())
            {
                stmt.executeUpdate("INSERT INTO BIGBOARD (NAME) VALUES ('" + monhoc.getTenMonHoc() + "')");
                String sql = "CREATE TABLE " + monhoc.getTenMonHoc() + " " +
                        "(ID INTEGER PRIMARY KEY AUTOINCREMENT    NOT NULL," +
                        " MA_DE          INT     NOT NULL, " + 
                        " DAP_AN         INT     NOT NULL)"; 
                stmt.executeUpdate(sql);
            }
            else
            {
                String sql = "DELETE FROM " + monhoc.getTenMonHoc();
                stmt.executeUpdate(sql);    
            }

            for (int i = 0; i < monhoc.getDsDapAn().size(); i++)
            {
                String sql = "INSERT INTO " + monhoc.getTenMonHoc() + " (MA_DE,DAP_AN) VALUES (" + 
                            Integer.toString(monhoc.getDsDapAn().get(i).getMaDe()) + ", '" + 
                            monhoc.getDsDapAn().get(i).getCauTraLoi() + "')";
                System.out.println("hello");
                stmt.executeUpdate(sql);
            }
        }
        catch (Exception e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public ArrayList<MonHoc> read()
    {
        ArrayList<MonHoc> res = new ArrayList<MonHoc>();
        try
        {
            ResultSet rs = stmt.executeQuery("SELECT * FROM BIGBOARD");
            while (rs.next())
            {
                String tenMonHoc = rs.getString("NAME");
                ResultSet _rs = stmt.executeQuery("SELECT * FROM " + tenMonHoc);
                ArrayList<DapAn> tmp = new ArrayList<DapAn>();
                while (_rs.next())
                {
                    tmp.add(new DapAn(_rs.getInt("MA_DE"), _rs.getString("DAP_AN")));
                }
                res.add(new MonHoc(tenMonHoc, tmp));
            }
        }
        catch (Exception e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return res;
    }
}