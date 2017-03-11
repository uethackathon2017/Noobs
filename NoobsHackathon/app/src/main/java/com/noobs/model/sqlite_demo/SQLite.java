import java.sql.*;

public class SQLite
{
    private Connection c;
    private Statement stmt;

    public void open()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            stmt = c.createStatement();
        }  
        catch (Exception e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Opened database successfully");
    }

  	public void create()
  	{
        try
        {    
      		String sql = "CREATE TABLE COMPANY " +
                   	"(ID INT PRIMARY KEY     NOT NULL," +
                   	" NAME           TEXT    NOT NULL, " + 
                   	" AGE            INT     NOT NULL, " + 
                   	" ADDRESS        CHAR(50), " + 
                   	" SALARY         REAL)"; 
      		stmt.executeUpdate(sql);
        } 
        catch (Exception e)
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());    
        }
    	System.out.println("Table created successfully");
  	}
}