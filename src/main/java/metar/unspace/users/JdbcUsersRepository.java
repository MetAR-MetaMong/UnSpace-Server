package metar.unspace.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUsersRepository implements UsersRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUsersRepository(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    @Override
    public Optional<User> findById(String uid) {
        final String sql = "SELECT * FROM Users WHERE user_id = ?";
        List<User> users = this.jdbcTemplate.query(sql, new UserRowMapper(), uid);
        return users.stream().findFirst();
    }

    @Override
    public boolean checkIfExist(String uid) {
        final String sql = "SELECT COUNT(*) FROM Users WHERE user_id = ?";
        Optional<Integer> count = Optional.ofNullable(this.jdbcTemplate.queryForObject(sql, Integer.class, uid));
        return count.orElse(0) > 0;
    }

    @Override
    public boolean updateUserInfo(User user) {
        final String sql = "UPDATE Users SET phone = ?, email = ?, name = ? WHERE user_id = ?";
        int rowsAffected = this.jdbcTemplate.update(sql, user.getPhone(), user.getEmail(), user.getName(), user.getUser_id());
        return rowsAffected > 0;
    }

    @Override
    public boolean insertUserInfo(User user) {
        final String sql = "INSERT INTO Users (user_id, phone, email, name) VALUES (?, ?, ?, ?)";
        int rowsAffected = this.jdbcTemplate.update(sql, user.getUser_id(), user.getPhone(), user.getEmail(), user.getName());
        return rowsAffected > 0;
    }


    private static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUser_id(rs.getString("user_id"));
            user.setName(rs.getString("name"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            return user;
        }

    }
}
