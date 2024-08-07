package metar.unspace.rentals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcRentalsRepository implements RentalsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRentalsRepository(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public List<Rental> findByPerson(String id, LocalDateTime startTime, LocalDateTime endTime) {
        final String sql = "SELECT r.rental_id, r.facility, r.starttime, r.endtime, r.user_id, u.name AS user_name, f.facility_name " +
                "FROM Rentals r " +
                "JOIN Users u ON r.user_id = u.user_id " +
                "JOIN Facilities f ON r.facility = f.facility_id " +
                "WHERE r.user_id = ? AND r.starttime <= ? AND r.endtime >= ?";

        return this.jdbcTemplate.query(sql, new RentalRowMapper(), id, endTime, startTime);
    }

    @Override
    public List<Rental> findByRoom(String id, LocalDateTime startTime, LocalDateTime endTime) {
        final String sql = "SELECT r.rental_id, r.facility, r.starttime, r.endtime, r.user_id, u.name AS user_name, f.facility_name " +
                "FROM Rentals r " +
                "JOIN Users u ON r.user_id = u.user_id " +
                "JOIN Facilities f ON r.facility = f.facility_id " +
                "WHERE r.facility = ? AND r.starttime <= ? AND r.endtime >= ?";

        return this.jdbcTemplate.query(sql, new RentalRowMapper(), id, endTime, startTime);
    }

    @Override
    public int create(RentalDTO.PostRequest rental) {
        final String sql = "INSERT INTO Rentals (facility, starttime, endtime, user_id) VALUES (?, ?, ?, ?)";
        return this.jdbcTemplate.update(sql,
                rental.getFacilityId(), rental.getStartTime(), rental.getEndTime(), rental.getUserId());
    }

    private static class RentalRowMapper implements RowMapper<Rental> {
        @Override
        public Rental mapRow(ResultSet rs, int rowNum) throws SQLException {
            Rental rental = new Rental();
            rental.setId(rs.getInt("rental_id"));
            rental.setFacility_id(rs.getInt("facility"));
            rental.setUser_id(rs.getString("user_id"));
            rental.setFacilityName(rs.getString("facility_name"));
            rental.setStarttime(rs.getString("starttime"));
            rental.setEndtime(rs.getString("endtime"));
            rental.setUsername(rs.getString("user_name"));
            return rental;
        }
    }

}
