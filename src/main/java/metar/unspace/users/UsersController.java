package metar.unspace.users;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    @Autowired
    private UsersService service;

    @GetMapping("/users")
    public ResponseEntity<UserDTO.Response> search(@RequestParam(name = "user_id") String uid ) {
        UserDTO.Response response = service.findById(uid);
        if(response == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/users")
    public ResponseEntity<UserDTO.Response> overwrite(@Valid @RequestBody UserDTO.PutRequest dto){
        UserDTO.Response response = service.insertOrUpdate(dto);
        if(response == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok(response);
        }
    }

}
