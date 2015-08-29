package org.fasttrackit.dev.todolist;

import java.sql.*;

/**
 * Created by user376 on 29.08.2015.
 */
public class DataBase {
    private String whatToDo;
    private boolean isDone;
    public DataBase(String whatToDo,boolean isDone)
    {
        this.whatToDo=whatToDo;
        this.isDone=isDone;
    }

    public DataBase()
    {
    }
    public void demoCreate() throws ClassNotFoundException, SQLException {

        System.out.println("incep");
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Dan_Agenda";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        System.out.println("am obtinut conex");
        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO \"mytasklist\" (activitate,stare) VALUES (?,?)");
        pSt.setString(1, whatToDo);
        pSt.setBoolean(2,isDone);


        System.out.println("statement");
        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        System.out.println("executat");
        // 6. close the objects
        pSt.close();
        conn.close();
        System.out.println("gata");
    }
    public static void demoRead(MyListOfToDoMock mm) throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Dan_Agenda";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT activitate,stare,id FROM \"mytasklist\" where stare=false");

        // 6. iterate the result set and print the values
        while (rs.next()) {
            mm.getList().add(new ToDoBean(rs.getInt("id"), rs.getString("activitate")));
            System.out.println( rs.getString("activitate"));
        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();
    }
    private static void demoDelete() throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Dan_Agenda";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("DELETE FROM \"Persoana\" WHERE id=?");
        pSt.setLong(1, 11);

        // 5. execute a prepared statement
        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were deleted.");
        // 6. close the objects
        pSt.close();
        conn.close();
    }
}
