package metar.unspace.rentals.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Rental {
    private int id;
    private String userId;
    private String facilityName;
    private LocalDateTime start;
    private LocalDateTime end;
    private String username;
}
