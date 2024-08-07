package metar.unspace.users;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class UserDTO {

    @Getter
    public static class PutRequest {
        @NotNull(message = "id cannot be null")
        private String id;

        @NotNull(message = "name cannot be null")
        private String name;

        @NotNull(message = "email cannot be null")
        private String email;

        @NotNull(message = "phone cannot be null")
        private String phone;
    }

    @Getter
    public static class Response {
        private String id;
        private String name;
        private String email;
        private String phone;

        public Response(User user) {
            this.id = user.getUser_id();
            this.name = user.getName();
            this.email = user.getEmail();
            this.phone = user.getPhone();
        }
    }

}
