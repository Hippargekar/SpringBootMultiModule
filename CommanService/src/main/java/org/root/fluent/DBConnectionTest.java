package org.root.fluent;

public class DBConnectionTest {

    public static void main(String[] args) {
        DBConnection fluent = DBConnection.build().enableSsl(true).setHost("localhost").setTimeout(1).setPort(1425).connect();
        DBConnection.build().setHost("localhost").setPort(1425).connect();
    }
}
