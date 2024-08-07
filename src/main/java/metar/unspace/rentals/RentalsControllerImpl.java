package metar.unspace.rentals;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalsControllerImpl implements RentalsController{

    @Autowired
    private RentalsService rentalsService;

    @Override
    @GetMapping("/rentals")
    public ResponseEntity<List<RentalDTO.Response>> search(
            @RequestParam(name = "user_id", required = false) String userId,
            @RequestParam(name = "facility_id", required = false) String facilityId,
            @RequestParam(name = "startTime") String startTime,
            @RequestParam(name = "endTime", required = false) String endTime) {

        List<Rental> domain;
        if(facilityId == null || facilityId.isEmpty()){
            if(endTime == null || endTime.isEmpty()){
                domain = rentalsService.findRentByID(new RentalDTO.GetRequest(userId, startTime));
            } else {
                domain = rentalsService.findRentByID(new RentalDTO.GetRequest(userId, startTime, endTime));
            }

        } else if(userId == null || userId.isEmpty()) {
            if(endTime == null || endTime.isEmpty()){
                domain = rentalsService.findRentByRoom(new RentalDTO.GetRequest(facilityId, startTime));
            } else {
                domain = rentalsService.findRentByRoom(new RentalDTO.GetRequest(facilityId, startTime, endTime));
            }

        } else {
            return ResponseEntity.badRequest().build();
        }

        List<RentalDTO.Response> response = domain.stream().map(RentalDTO.Response::new).toList();
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping("/rentals")
    public ResponseEntity<Void> rent(@Valid @RequestBody RentalDTO.PostRequest request) {
        boolean check = rentalsService.makeRentals(request);
        if(!check){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

}
