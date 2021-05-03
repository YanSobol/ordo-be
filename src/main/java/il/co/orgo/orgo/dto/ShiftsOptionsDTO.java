package il.co.orgo.orgo.dto;

import il.co.orgo.orgo.model.ShiftsOptions;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import java.util.Date;

@Data
public class ShiftsOptionsDTO {

    private Long userId;

    private Long shiftId;

    public ShiftsOptions toShiftsOptions(ShiftsOptionsDTO shiftsOptionsDTO){
        ShiftsOptions shiftsOptions = new ShiftsOptions();
        shiftsOptions.setUserId(shiftsOptionsDTO.getUserId());
        shiftsOptions.setShiftId(shiftsOptionsDTO.getShiftId());
        shiftsOptions.setUpdated(new Date());
        shiftsOptions.setCreated(new Date());

        return shiftsOptions;
    }

    public ShiftsOptionsDTO toShiftsOptionsDTO(ShiftsOptions shiftsOptions){
        ShiftsOptionsDTO shiftsOptionsDTO = new ShiftsOptionsDTO();
        shiftsOptionsDTO.setUserId(shiftsOptions.getUserId());
        shiftsOptionsDTO.setShiftId(shiftsOptions.getShiftId());

        return shiftsOptionsDTO;
    }

}
