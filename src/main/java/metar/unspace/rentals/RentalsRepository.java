package metar.unspace.rentals;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalsRepository {
     List<Rental> findByPerson(String id, LocalDateTime startTime, LocalDateTime endTime);
     List<Rental> findByRoom(String id, LocalDateTime startTime, LocalDateTime endTime);
     int create(RentalDTO.PostRequest rental);
}
