package com;

import java.util.ArrayList;
import java.util.Scanner;

public class TravelApp {
    public static void main(String[] args) {
        // Travel 애플리케이션 클래스

        TravelService travelService = new TravelService();

        Scanner sc = new Scanner( System.in );

        String menuInput = null;

        whileLoop:
        while ( true ) {
            System.out.println("========================================================================");
            System.out.println();
            System.out.println( "1. 전체 목록" );
            System.out.println( "2. 권역별 관광지 목록" );
            System.out.println( "3. 검색 결과 목록" );
            System.out.println();
            System.out.println( "4. 종료" );
            System.out.println();
            System.out.println("========================================================================");
            System.out.println();

            System.out.print( "번호 입력 : " );

            menuInput = sc.nextLine();

            switch( menuInput ) {
                case "1":

                    System.out.print( "페이지( 10개의 데이터씩 ) : " );
                    String strPage = sc.nextLine();
                    try {
                        int page = Integer.parseInt( strPage.trim() );

                        ArrayList<TravelVO> travelList = travelService.TravelPage( page );
                        for ( TravelVO travel : travelList ) {

                            String data = String.format( "%s %s %s %s %s %s"
                                    , travel.getNo(), travel.getDistrict(), travel.getTitle()
                                    , travel.getDescription(), travel.getAddress(), travel.getPhone()
                            );
                            System.out.println( data );

                        }
                    } catch (NumberFormatException e) {
                        System.out.println( "정수 값을 입력하세요." );
                    }

                    break;

                case "2":

                    System.out.print( "권역 이름 (수도권, 강원권, 충청권, 전라권, 경상권, 제주권) : " );
                    String name = sc.nextLine();
                    ArrayList<TravelVO> region = travelService.TravelDistrict( name );

                    for ( TravelVO travel : region ) {
                        String data = String.format( "%s %s %s %s %s %s"
                                , travel.getNo(), travel.getDistrict(), travel.getTitle()
                                , travel.getDescription(), travel.getAddress(), travel.getPhone()
                        );
                        System.out.println( data );
                    }

                    System.out.println();
                    break;

                case "3":
                    System.out.println();

                    System.out.print(" 검색할 내용 : ");
                    String msg = sc.nextLine();
                    ArrayList<TravelVO> keyword = travelService.TravelSearch( msg );
                    // travelList = travelDao.TravelSearch( search );

                    for ( TravelVO travel : keyword ) {
                        String data = String.format( "%s %s %s %s %s %s"
                                , travel.getNo(), travel.getDistrict(), travel.getTitle()
                                , travel.getDescription(), travel.getAddress(), travel.getPhone()
                        );
                        System.out.println( data );
                    }

                    System.out.println();
                    break;

                case "4":
                    sc.close();
                    break whileLoop;

                default:
                    System.out.println();
                    System.out.println( "잘못된 값을 입력하셨습니다." );
                    System.out.println( "입력은 1~3, 4 을 입력해주세요." );
                    System.out.println();
                    break;
            }
        }
        System.out.println();
        System.out.println( "시스템을 종료합니다." );
        System.out.println();

    }
}
