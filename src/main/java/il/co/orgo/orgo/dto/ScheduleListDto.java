package il.co.orgo.orgo.dto;

import il.co.orgo.orgo.model.ShiftsOptions;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScheduleListDto {

    List<ShiftsOptionsDTO> options;

    public void fromShiftsOptionsToDto(List<ShiftsOptions> shiftsOptions){
        options = new ArrayList<ShiftsOptionsDTO>();
        shiftsOptions.forEach(shiftsOptionsVar -> {
            options.add(new ShiftsOptionsDTO().toShiftsOptionsDTO(shiftsOptionsVar));
        });
    }
}
