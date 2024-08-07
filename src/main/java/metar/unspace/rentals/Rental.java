package metar.unspace.rentals;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rental {
    private int id;
    private int facility_id;
    private String user_id;
    private String facilityName;
    private String starttime;
    private String endtime;
    private String username;
}
