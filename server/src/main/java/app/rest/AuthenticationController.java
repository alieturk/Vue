package app.rest;


import app.APIConfig;
import app.Exceptions.NotAcceptableException;
import app.models.User;
import app.repositories.UserRepository;
import app.security.JWToken;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    APIConfig apiConfig;

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/login")
    public ResponseEntity<User> authenticateUser(@RequestBody ObjectNode data){
        String email = data.get("email").asText();
        String password = data.get("password").asText();

        List users = this.userRepository.findByQuery("User_find_By_Query",email);
        User user = users.size() == 1 ? (User) users.get(0) : null;
        if (user == null|| !user.isPasswoordSame(password)){
            throw new NotAcceptableException("Cannot authenticate account with email=" + email);
        }
        // Issue a token for the account, valid for some time
        JWToken jwToken = new JWToken(user.getName(), user.getId(), user.getRole());
        String tokenString = jwToken.encode(this.apiConfig.getIssuer(),
                this.apiConfig.getPassphrase(), this.apiConfig.getTokenDurationOfValidity());
        return ResponseEntity.accepted()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenString)
                .body(user);
    }
}
