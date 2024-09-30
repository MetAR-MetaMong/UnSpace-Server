package metar.unspace.rentals;

import jakarta.validation.Valid;
import metar.unspace.rentals.dto.Rental;
import metar.unspace.rentals.dto.RentalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalsControllerImpl implements RentalsController {

    @Autowired
    private RentalsService rentalsService;

    @Override
    @GetMapping("/")
    public ResponseEntity<Void> ping(){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    @GetMapping("/rentals")
    public ResponseEntity<List<RentalDTO.Response>> search(
            @RequestParam(name = "user_id", required = false) String userId,
            @RequestParam(name = "facility_name", required = false) String facilityName,
            @RequestParam(name = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String startTime,
            @RequestParam(name = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String endTime) {

        List<Rental> domain;
        if (!StringUtils.hasText(userId) && !StringUtils.hasText(facilityName)) {
            return ResponseEntity.badRequest().build();
        }

        if (StringUtils.hasText(userId)) {
            domain = rentalsService.findRentByID(new RentalDTO.GetRequest(userId, startTime, endTime));
        } else {
            domain = rentalsService.findRentByRoom(new RentalDTO.GetRequest(facilityName, startTime, endTime));
        }
        List<RentalDTO.Response> response = domain.stream().map(RentalDTO.Response::new).toList();
        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("/rentals")
    public ResponseEntity<Void> rent(@Valid @RequestBody RentalDTO.PostRequest request) {
        boolean check = rentalsService.makeRentals(request);
        if (!check) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

}
