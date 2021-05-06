package il.co.orgo.orgo.service.implementation;

import il.co.orgo.orgo.model.ShiftsOptions;
import il.co.orgo.orgo.repository.IShiftsOptionsRepository;
import il.co.orgo.orgo.service.IShiftsOptionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ShiftsOptionsImplementation implements IShiftsOptionsService {

    private final IShiftsOptionsRepository iShiftsOptionsRepository;

    @Autowired
    public ShiftsOptionsImplementation(IShiftsOptionsRepository iShiftsOptionsRepository) {
        this.iShiftsOptionsRepository = iShiftsOptionsRepository;
    }


    @Override
    public void submitUserShiftsOptions(ShiftsOptions options) {
        iShiftsOptionsRepository.save(options);
        log.info("IN ShiftsOptionsImplementation.submitUserShiftsOptions, shifts options ware submitted");
    }

    @Override
    public void submitUserShiftsOptions(List<ShiftsOptions> shiftsOptions) {
        shiftsOptions.forEach(iShiftsOptionsRepository::save);
        log.info("IN ShiftsOptionsImplementation.submitUserShiftsOptions, shifts options ware submitted");
    }

    @Override
    public List<ShiftsOptions> getAll() {
        List<ShiftsOptions> allShiftsOptions = iShiftsOptionsRepository.findAll();
        log.info("IN ShiftsOptionsImplementation.getAll - {} shifts options found", allShiftsOptions);
        return allShiftsOptions;
    }
}
