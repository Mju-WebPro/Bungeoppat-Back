package server.api.webpro.store.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequest {
    private Long id;
    private String storeName;
    private String operateTime;
    private String menu;
    private double latitude;
    private double longitude;
}
