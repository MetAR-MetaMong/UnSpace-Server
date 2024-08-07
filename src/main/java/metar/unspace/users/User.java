package metar.unspace.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String user_id;
    private String name;
    private String phone;
    private String email;

    public User() { }
    public User(UserDTO.PutRequest user) {
        this.user_id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
    }
}
