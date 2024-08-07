package metar.unspace.users;

import java.util.Optional;

public interface UsersRepository {
     Optional<User> findById(String uid);
     boolean checkIfExist(String uid);
     boolean updateUserInfo(User user);
     boolean insertUserInfo(User user);

}
