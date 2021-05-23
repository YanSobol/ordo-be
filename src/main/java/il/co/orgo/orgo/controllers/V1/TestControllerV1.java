package il.co.orgo.orgo.controllers.V1;

import il.co.orgo.orgo.dto.ShiftsListDTO;
import il.co.orgo.orgo.dto.ShiftsOptionsDTO;
import il.co.orgo.orgo.dto.UserDto;
import il.co.orgo.orgo.dto.UserSignupDto;
import il.co.orgo.orgo.model.ShiftsOptions;
import il.co.orgo.orgo.model.User;
import il.co.orgo.orgo.service.IScheduleService;
import il.co.orgo.orgo.service.IShiftsOptionsService;
import il.co.orgo.orgo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "api/v1/test/")
public class TestControllerV1 {


    private final IShiftsOptionsService shiftsOptionsService;
    private final IScheduleService iScheduleService;
    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public TestControllerV1(IShiftsOptionsService shiftsOptionsService, IScheduleService iScheduleService, UserService userService, ModelMapper mapper) {
        this.shiftsOptionsService = shiftsOptionsService;
        this.iScheduleService = iScheduleService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("submit_options")
    public String submitShiftsOptions(@RequestBody ShiftsOptionsDTO shiftsOptionsDTO) {

        ShiftsOptions shiftsOptions = shiftsOptionsDTO.toShiftsOptions(shiftsOptionsDTO);
        shiftsOptionsService.submitUserShiftsOptions(shiftsOptions);

        return "submitted";
    }

    @PostMapping("submit_options_list")
    public String submitListOfShiftsOptions(@RequestBody ShiftsListDTO shiftsListDTO) {

        shiftsListDTO.getOptions().forEach(option->{
            ShiftsOptions shiftsOptions = option.toShiftsOptions(option);
            shiftsOptionsService.submitUserShiftsOptions(shiftsOptions);
        });

        return "submitted";
    }

    @PostMapping("get_options")
    public ShiftsListDTO getShiftOptions() {

        ShiftsListDTO shiftsListDTO = new ShiftsListDTO();
        shiftsListDTO.fromShiftsOptionsToDto(shiftsOptionsService.getAll());

        return shiftsListDTO;
    }
    @PostMapping("signup")
    public UserDto sign(@RequestBody UserDto signRequestDto) {

        User user = mapper.map(signRequestDto, User.class);

        userService.register(user);
        return mapper.map(user, UserDto.class);
    }
    @PutMapping("update")
    public UserDto update(@RequestBody UserDto signRequestDto) {

        User userFromRequest = mapper.map(signRequestDto, User.class);
        userService.update(userFromRequest);
        return mapper.map(userService.findByUsername(userFromRequest.getUsername()), UserDto.class);
    }

    @PostMapping("signup1")
    public UserDto sign1(@RequestBody UserSignupDto signRequestDto) {

        User user = mapper.map(signRequestDto, User.class);
        userService.register(user);
        return mapper.map(user, UserDto.class);
    }
}