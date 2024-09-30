package metar.unspace.rentals;

import metar.unspace.rentals.dto.Rental;
import metar.unspace.rentals.dto.RentalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcRentalsRepository implements RentalsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRentalsRepository(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public List<Rental> findByPerson(String id, String startTime, String endTime) {
        final String sql = "SELECT * FROM Rentals WHERE user_id = ? AND start >= ? AND end <= ?";
        return this.jdbcTemplate.query(sql, new RentalRowMapper(), id, startTime, endTime);
    }


    @Override
    public List<Rental> findByRoom(String id, String startTime, String endTime) {
        final String sql = "SELECT * FROM Rentals WHERE facility_name = ? AND start >= ? AND end <= ?";
        return this.jdbcTemplate.query(sql, new RentalRowMapper(), id, startTime, endTime);
    }


    @Override
    public int create(String userId, String userName, String facilityName, String start, String end) {
        final String checkSql = "SELECT COUNT(*) FROM Rentals WHERE user_id = ? AND facility_name = ? AND start = ? AND end = ?";
        Integer count = this.jdbcTemplate.queryForObject(checkSql, Integer.class, userId, facilityName, start, end);
        if (count != null && count > 0) {
            return 0;
        }
        final String insertSql = "INSERT INTO Rentals (start, end, user_id, user_name, facility_name) VALUES (?, ?, ?, ?, ?)";
        return this.jdbcTemplate.update(insertSql, start, end, userId, userName, facilityName);
    }

    private static class RentalRowMapper implements RowMapper<Rental> {
        @Override
        public Rental mapRow(ResultSet rs, int rowNum) throws SQLException {
            Rental rental = new Rental();
            rental.setId(rs.getInt("rental_id"));
            rental.setUserId(rs.getString("user_id"));
            rental.setUsername(rs.getString("user_name"));
            rental.setFacilityName(rs.getString("facility_name"));
            rental.setStart(rs.getTimestamp("start").toLocalDateTime());
            rental.setEnd(rs.getTimestamp("end").toLocalDateTime());
            return rental;
        }
    }


}
