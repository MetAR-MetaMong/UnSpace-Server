package metar.unspace.rentals;

import metar.unspace.rentals.dto.Rental;
import metar.unspace.rentals.dto.RentalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RentalsServiceImpl implements RentalsService {

    private static final DateTimeFormatter MARIADB_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    @Autowired
    private RentalsRepository rentalsRepository;

    @Override
    public List<Rental> findRentByID(RentalDTO.GetRequest dto) {
        String start = formatForMariaDB(dto.getStartTime());
        String end = formatForMariaDB(dto.getEndTime());
        return rentalsRepository.findByPerson(dto.getId(), start, end);
    }

    @Override
    public List<Rental> findRentByRoom(RentalDTO.GetRequest dto) {
        String start = formatForMariaDB(dto.getStartTime());
        String end = formatForMariaDB(dto.getEndTime());
        return rentalsRepository.findByRoom(dto.getId(), start, end);
    }

    @Override
    public boolean makeRentals(RentalDTO.PostRequest dto) {
        String start = formatForMariaDB(dto.getStart());
        String end = formatForMariaDB(dto.getEnd());
        int check = rentalsRepository.create(dto.getUserId(), dto.getUserName(), dto.getFacilityName(), start, end);
        return check == 1;
    }

    private String formatForMariaDB(String isoDateTime) {
        LocalDateTime dateTime = LocalDateTime.parse(isoDateTime, ISO_FORMATTER);
        return dateTime.format(MARIADB_FORMATTER);
    }

    private String formatForAPI(LocalDateTime dateTime) {
        return dateTime.format(ISO_FORMATTER);
    }
}
