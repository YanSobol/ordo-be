package il.co.orgo.orgo.service.implementation;

import il.co.orgo.orgo.model.ShiftsOptions;
import il.co.orgo.orgo.repository.IShiftsOptionsRepository;
import il.co.orgo.orgo.service.IShiftsOptionsService;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiftsOptionsImplementation implements IShiftsOptionsService {

    private final IShiftsOptionsRepository iShiftsOptionsRepository;

    @Autowired
    public ShiftsOptionsImplementation(IShiftsOptionsRepository iShiftsOptionsRepository) {
        this.iShiftsOptionsRepository = iShiftsOptionsRepository;
    }


    @Override
    public void submitUserShiftsOptions(ShiftsOptions options) {
        iShiftsOptionsRepository.save(options);
    }
}
