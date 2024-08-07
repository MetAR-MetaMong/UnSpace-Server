package metar.unspace.rentals;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RentalsController {

    public ResponseEntity<List<RentalDTO.Response>> search(
            @RequestParam(name = "user_id", required = false) String userId,
            @RequestParam(name = "facility_id", required = false) String facilityId,
            @RequestParam(name = "startTime") String startTime,
            @RequestParam(name = "endTime", required = false) String endTime);

    public ResponseEntity<Void> rent(@RequestBody RentalDTO.PostRequest request);

}
