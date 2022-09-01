package br.cliniconect.medical.util;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ScriptSql {

    public static void run(String fileName) {

        String SQLScriptFilePath = "src/main/data/" + fileName;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/medical-clinic", "postgres", "sigfpto");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ScriptRunner sr = new ScriptRunner(con);
            Reader reader = new BufferedReader(new FileReader(SQLScriptFilePath));
            sr.runScript(reader);
        } catch (Exception e) {
            System.err.println("Failed to Execute" + SQLScriptFilePath + " The error is " + e.getMessage());
        }
    }
}