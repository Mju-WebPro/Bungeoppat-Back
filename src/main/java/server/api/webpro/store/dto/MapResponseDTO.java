package server.api.webpro.store.dto;

import java.util.List;

public class MapResponseDTO {
    private List<Address> addresses;
    public static class Address {
        private String roadAddressName; // 도로명 주소
        private String jibunAddressName; // 지번 주소
        private Double x; // 경도
        private Double y; // 위도
    }
}
