package il.co.orgo.orgo.dto;

import il.co.orgo.orgo.model.ShiftsOptions;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShiftsListDTO {

    List<ShiftsOptionsDTO> options;

    public void fromShiftsOptionsToDto(List<ShiftsOptions> shiftsOptions){
        options = new ArrayList<ShiftsOptionsDTO>();
        shiftsOptions.forEach(shiftsOptionsVar -> {
            options.add(new ShiftsOptionsDTO().toShiftsOptionsDTO(shiftsOptionsVar));
        });
    }

    public void fromDtoToShiftsOptions(List<ShiftsOptionsDTO> shiftsOptionsDto){
        List<ShiftsOptions> shiftsOptions = new ArrayList<ShiftsOptions>();
        shiftsOptionsDto.forEach(optionsDto->{
            shiftsOptions.add(optionsDto.toShiftsOptions(optionsDto));
        });
    }
}
