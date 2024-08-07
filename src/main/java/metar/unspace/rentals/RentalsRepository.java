package metar.unspace.rentals;

import java.time.LocalDateTime;
import java.util.List;

public interface RentalsRepository {
    public List<Rental> findByPerson(String id, LocalDateTime startTime, LocalDateTime endTime);
    public List<Rental> findByRoom(String id, LocalDateTime startTime, LocalDateTime endTime);
    public int create(RentalDTO.PostRequest rental);
}
