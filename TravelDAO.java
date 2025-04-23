package com;

import java.sql.*;
import java.util.ArrayList;

public class TravelDAO {
    // Travel 테이블에 대한 CRUD를 제공하는 DAO 클래스

    private Connection conn;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public TravelDAO() {
        String url = "jdbc:mysql://localhost:3306/travel_db";
        String user = "root";
        String password = "!123456";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("[에러] " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("[에러] " + e.getMessage());
        }

    }

    public ArrayList<TravelVO> findAll() {
        // 전체 목록을 페이지 별로 보여주기

        ArrayList<TravelVO> lists = new ArrayList<>();

        try {
            String sql = "select no, district, title, description, address, phone from travel";
            pstmt = this.conn.prepareStatement( sql );

            rs = pstmt.executeQuery();

            while( rs.next() ) {

                TravelVO travel = new TravelVO(
                    rs.getString( "no" ),
                    rs.getString( "district" ),
                    rs.getString( "title" ),
                    rs.getString( "description" ),
                    rs.getString( "address" ),
                    rs.getString( "phone" )
                );

                lists.add(travel);

                /*
                if(Integer.parseInt( rs.getString("no") ) % 10 == 0 ) {
                    System.out.println("no : " + Integer.parseInt( rs.getString("no") ));
                }
                */

            }
        } catch (SQLException e) {
            System.out.println( "[에러] " + e.getMessage() );
        } finally {
            if( pstmt != null ) try { pstmt.close(); } catch (SQLException e) {  }
            if( rs != null ) try { rs.close(); } catch (SQLException e) { }
        }

        return lists;
    }

    public ArrayList<TravelVO> findRegion(String region) {
        // 권역별 관광지 목록 보여주기

        ArrayList<TravelVO> lists = new ArrayList<>();

        try {
            String sql = "select no, district, title, description, address, phone from travel where district like ?";
            pstmt = this.conn.prepareStatement(sql);

            pstmt.setString(1, region + "%" );

            rs = pstmt.executeQuery();

            while (rs.next()) {
                TravelVO travel = new TravelVO(
                        rs.getString("no"),
                        rs.getString("district"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("address"),
                        rs.getString("phone")
                );

                lists.add(travel);
            }
        } catch (SQLException e) {
            System.out.println( "[에러] " + e.getMessage() );
        } finally {
            if( pstmt != null ) try { pstmt.close(); } catch (SQLException e) {}
            if( rs != null ) try { rs.close(); } catch (SQLException e) {}
        }

        return lists;
    }

    public ArrayList<TravelVO> findSearch(String keyword) {
        // 검색 결과를 목록으로 보여주기

        ArrayList<TravelVO> lists = new ArrayList<>();

        try {
            String sql = "select no, district, title, description, address, phone from travel where district like ? or title like ? or description like ? or address like ? ";
            pstmt = this.conn.prepareStatement( sql );

            for ( int i = 1; i <= 4; i++ ) {
                pstmt.setString(i, "%" + keyword + "%");
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                TravelVO travel = new TravelVO(
                        rs.getString("no"),
                        rs.getString("district"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
                lists.add(travel);
            }
        } catch (SQLException e) {
            System.out.println( "[에러] " + e.getMessage() );
        } finally {
            if( pstmt != null ) try { pstmt.close(); } catch (SQLException e) { }
            if( rs != null ) try { rs.close(); } catch (SQLException e) { }
        }

        return lists;

    }
}
