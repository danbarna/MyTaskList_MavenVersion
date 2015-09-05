package org.fasttrackit.dev.todolist;

import java.sql.*;

/**
 * Created by user376 on 29.08.2015.
 */
public class DataBase {
    private static Connection conn;


    public DataBase() {
    }

    public void demoCreate(String whatToDo, boolean isDone, int userId) throws ClassNotFoundException, SQLException {

        System.out.println("incep");
        // 1. load driver
        if (conn == null) {
            conn = getConnection();
        } else {
            System.out.println("----------------------deja am conex");
        }
        System.out.println("am obtinut conex");
        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO \"mytasklist\" (activitate,stare,\"userId\") VALUES (?,?,?)");
        pSt.setString(1, whatToDo);
        pSt.setBoolean(2, isDone);
        pSt.setInt(3, userId);


        System.out.println("statement");
        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        System.out.println("executat");
        // 6. close the objects
        pSt.close();
        System.out.println("gata");
    }

    public void demoRead(MyListOfToDoMock mm, int userId) throws ClassNotFoundException, SQLException {
        // 1. load driver

        System.out.println("in db userid:" + userId);

        if (conn == null) {
            System.out.println("wwwwwwwwwwwww uite conex");
            conn = getConnection();
        } else {
            System.out.println("----------------------deja am conex");
        }
        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        String query = "SELECT activitate,stare,id FROM \"mytasklist\" where stare=false and \"userId\" = " + userId;
        System.out.println("q read:" + query);
        ResultSet rs = st.executeQuery(query);

        // 6. iterate the result set and print the values
        while (rs.next()) {
            mm.getList().add(new ToDoBean(rs.getInt("id"), rs.getString("activitate")));
            System.out.println(rs.getString("activitate"));
        }

        // 7. close the objects
        rs.close();
        st.close();

    }

    public void demoDelete(int key) throws ClassNotFoundException, SQLException {

        if (conn == null) {
            conn = getConnection();
        } else {
            System.out.println("----------------------deja am conex");
        }
        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("update \"mytasklist\" set stare=? where id=?");
        pSt.setBoolean(1, true);
        pSt.setInt(2, key);

        // 5. execute a prepared statement
        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were updated.");
        // 6. close the objects
        pSt.close();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/Dan_Agenda";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
