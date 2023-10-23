package chesstogether.web.app.controller;

import chesstogether.web.app.model.UserEntity;
import chesstogether.web.app.service.UserService;
import chesstogether.web.app.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
    private final UserService userService;

    @Autowired
    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ResponseEntity<UserEntity> showHomePage() {
        String username = SecurityUtil.getSessionUser();
        System.out.println(username);
        if (username != null) {
            UserEntity user = userService.findByUsername(username);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.ok(new UserEntity());
        }
    }
}