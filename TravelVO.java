package com;

public class TravelVO {
    // Travel 테이블에 대한 VO 클래스

    /*

    - 순번 : no
    - 지역 : district
    - 제목 : title
    - 설명 : description
    - 주소 : address
    - 전화번호 : phone

     */

    private String no;
    private String district;
    private String title;
    private String description;
    private String address;
    private String phone;

    public TravelVO(String no, String district, String title, String description, String address, String phone) {
        this.no = no;
        this.district = district;
        this.title = title;
        this.description = description;
        this.address = address;
        this.phone = phone;
    }

    public String getNo() {
        return no;
    }

    public String getDistrict() {
        return district;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

}
