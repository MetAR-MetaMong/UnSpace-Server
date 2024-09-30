package metar.unspace.rentals;

import metar.unspace.rentals.dto.Rental;
import metar.unspace.rentals.dto.RentalDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalsRepository {
     List<Rental> findByPerson(String id, String startTime, String endTime);
     List<Rental> findByRoom(String id, String startTime, String endTime);
     int create(String userId, String userName, String facilityName, String start, String end);
}
