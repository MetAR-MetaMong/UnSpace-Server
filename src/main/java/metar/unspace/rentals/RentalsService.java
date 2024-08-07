package metar.unspace.rentals;

import java.util.List;

public interface RentalsService {
    public List<Rental> findRentByID(RentalDTO.GetRequest dto);
    public List<Rental> findRentByRoom(RentalDTO.GetRequest dto);
    public boolean makeRentals(RentalDTO.PostRequest dto);
}
