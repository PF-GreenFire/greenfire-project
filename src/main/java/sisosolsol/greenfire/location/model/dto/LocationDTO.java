package sisosolsol.greenfire.location.model.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LocationDTO {
    private Integer locationCode;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer areaCode;
}
