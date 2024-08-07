package metar.unspace.users;

import java.util.Optional;

public interface UsersRepository {
    public Optional<User> findById(String uid);

    public boolean checkIfExist(String uid);

    public boolean updateUserInfo(User user);

    public boolean insertUserInfo(User user);

}
