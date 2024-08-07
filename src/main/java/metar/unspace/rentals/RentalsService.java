package metar.unspace.rentals;

import java.util.List;

public interface RentalsService {
    List<Rental> findRentByID(RentalDTO.GetRequest dto);
    List<Rental> findRentByRoom(RentalDTO.GetRequest dto);
    boolean makeRentals(RentalDTO.PostRequest dto);
}
