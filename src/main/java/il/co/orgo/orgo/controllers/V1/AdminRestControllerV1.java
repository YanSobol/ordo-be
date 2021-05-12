package il.co.orgo.orgo.controllers.V1;

import il.co.orgo.orgo.dto.AdminUserDto;
import il.co.orgo.orgo.mapper.AdminUserMapper;
import il.co.orgo.orgo.mapper.UserMapper;
import il.co.orgo.orgo.model.User;
import il.co.orgo.orgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final AdminUserMapper mapper;
    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(AdminUserMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    //  Here we check users by id and get ALL information about them
    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        AdminUserDto result = mapper.toDto(user);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
