package metar.unspace.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository repository;

    public UserDTO.Response findById(String uid){
        Optional<User> result = repository.findById(uid);
        return result.map(UserDTO.Response::new).orElse(null);
    }

    public UserDTO.Response insertOrUpdate(UserDTO.PutRequest user){
        boolean check;
        User target = new User(user);
        if(repository.checkIfExist(target.getUser_id())){
            check = repository.updateUserInfo(target);
        } else {
            check = repository.insertUserInfo(target);
        }
        if(check){
            return new UserDTO.Response(target);
        } else {
            return null;
        }
    }
}
