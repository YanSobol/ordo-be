package il.co.orgo.orgo.controllers.V1;

import il.co.orgo.orgo.dto.ShiftsListDTO;
import il.co.orgo.orgo.dto.ShiftsOptionsDTO;
import il.co.orgo.orgo.dto.SignRequestDto;
import il.co.orgo.orgo.model.ShiftsOptions;
import il.co.orgo.orgo.service.IShiftsOptionsService;
import il.co.orgo.orgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/test/")
public class TestControllerV1 {


    private final IShiftsOptionsService shiftsOptionsService;

    @Autowired
    public TestControllerV1(IShiftsOptionsService shiftsOptionsService) {
        this.shiftsOptionsService = shiftsOptionsService;
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
}
