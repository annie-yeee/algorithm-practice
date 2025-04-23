package com;

import java.util.ArrayList;

public class TravelService {
    // Travel에 대한 로직 처리를 담당하는 클래스

    private TravelDAO travelDao;

    public TravelService() {
        this.travelDao = new TravelDAO();
    }

    public ArrayList<TravelVO> TravelPage( int page ) {
        ArrayList<TravelVO> lists = travelDao.findAll();

        if ( page < 1 ) {
            System.out.println( "1 이상의 숫자만 입력하세요." );
        }
        if ( ( page - 1 ) * 10 >= lists.size() ) {
            System.out.println( "해당 페이지에는 목록이 존재하지 않습니다." );
        }

        ArrayList list = new ArrayList();
        if ( page * 10 <= lists.size() ) {
            for (int i = ( page - 1 ) * 10; i < (page * 10); i++) {
                list.add( lists.get(i) );
            }
        } else {
            for ( int i = ( page - 1 ) * 10; i < lists.size(); i++ ) {
                list.add( lists.get(i) );
            }
        }

        return list;
    }

    public ArrayList<TravelVO> TravelDistrict( String region ) {
        // 수도권, 강원권, 충청권, 전라권, 경상권, 제주권
        ArrayList<TravelVO> list = travelDao.findRegion( region.trim() );
        if ( region.equals( "수도권" ) || region.equals( "강원권" ) || region.equals( "충청권" )
                || region.equals( "전라권" ) || region.equals( "경상권" ) || region.equals( "제주권" )  ) {
            if ( list.isEmpty() ) {
                System.out.println( "해당 권역은 존재하지 않습니다." );
            }
        } else {
            System.out.println( "보기에 맞게 권역을 입력해주세요." );
        }
        return list;
    }

    public ArrayList<TravelVO> TravelSearch( String keyword ) {
        ArrayList<TravelVO> list = travelDao.findSearch(keyword.trim());
        if ( list.isEmpty() ) {
            System.out.println( "검색 결과가 존재하지 않습니다." );

        }
        return list;
    }
}

