package com;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReadCSVEx01 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/travel_db";
        String user = "root";
        String password = "!123456";

        Connection conn = null;
        PreparedStatement pstmt = null;
        CSVReader csvReader = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            csvReader = new CSVReaderBuilder(new FileReader("C:/java/JavaProjects/Project_travel/src/main/java/travel.csv")).build();

            csvReader.readNext();

            List<String[]> lines = csvReader.readAll();

            String sql = "insert into travel ( district, title, description, address, phone ) values(?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            for (String[] line : lines) {
                System.out.println(line[0] + "\t" + line[1] + "\t" + line[2] + "\t" + line[3] + "\t" + line[4] + "\t" + line[5]);

                pstmt.setString(1, line[1]);
                pstmt.setString(2, line[2]);
                pstmt.setString(3, line[3]);
                pstmt.setString(4, line[4]);
                pstmt.setString(5, line[5]);

                pstmt.executeUpdate();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("[에러] " + e.getMessage() );
        } catch (FileNotFoundException e) {
            System.out.println( "[에러] " + e.getMessage() );
        } catch (CsvException e) {
            System.out.println("[에러] " + e.getMessage() );
        } catch (SQLException e) {
            System.out.println("[에러] " + e.getMessage() );
        } catch (IOException e) {
            System.out.println( "[에러] " + e.getMessage() );
        } finally {
            if ( csvReader != null ) try { csvReader.close(); } catch (IOException e) { }
        }
    }
}

