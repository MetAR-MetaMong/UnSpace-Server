package metar.unspace.rentals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalsServiceImpl implements RentalsService {

    @Autowired
    private RentalsRepository rentalsRepository;

    @Override
    public List<Rental> findRentByID(RentalDTO.GetRequest dto) {
        LocalDateTime endtime;
        if (dto.getEndTime().isEmpty()) {
            endtime = LocalDateTime.now();
        } else {
            endtime = dto.getEndTime().get();
        }
        return rentalsRepository.findByPerson(dto.getId(), dto.getStartTime(), endtime);
    }

    @Override
    public List<Rental> findRentByRoom(RentalDTO.GetRequest dto) {
        LocalDateTime endtime;
        if (dto.getEndTime().isEmpty()) {
            endtime = LocalDateTime.now();
        } else {
            endtime = dto.getEndTime().get();
        }
        return rentalsRepository.findByRoom(dto.getId(), dto.getStartTime(), endtime);
    }

    @Override
    public boolean makeRentals(RentalDTO.PostRequest dto) {
        int check = rentalsRepository.create(dto);
        return check == 1;
    }
}
