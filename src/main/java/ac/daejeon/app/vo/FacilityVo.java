package ac.daejeon.app.vo;


import lombok.Data;

@Data
public class FacilityVo {

    //편의 시설 idx
    private int convenienceFacilityIdx;

    //편의 시설 타입
    private String convenienceFacilityType;

    //편의 시설 명
    private String convenienceFacilityTitle;

    //편의시설 플래그
    private String convenienceFacilityFlag;

    //편의시설 위도
    private Double convenienceFacilityLatitude;

    //편의시설 경도
    private Double convenienceFacilityLongitude;

    //편의시설 위도 경도 알아내기 위한 검색어
    private String convenienceFacilitySearchText;

    //그룹으로 묶을지
    private boolean isGroupBy = false;

    //where 절 특정
    private boolean isWhereType = false;

    //where 절 특정
    private boolean isWhereIdx = false;


}
