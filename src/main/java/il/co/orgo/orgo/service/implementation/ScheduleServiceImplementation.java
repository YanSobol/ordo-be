package il.co.orgo.orgo.service.implementation;

import il.co.orgo.orgo.model.ScheduleShifts;
import il.co.orgo.orgo.model.ShiftsOptions;
import il.co.orgo.orgo.model.User;
import il.co.orgo.orgo.repository.IScheduleRepository;
import il.co.orgo.orgo.repository.IShiftsOptionsRepository;
import il.co.orgo.orgo.repository.UserRepository;
import il.co.orgo.orgo.service.IScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScheduleServiceImplementation implements IScheduleService {

    private final IScheduleRepository iScheduleRepository;
    private final IShiftsOptionsRepository iShiftsOptionsRepository;
    private final UserRepository userRepository;

    @Autowired
    public ScheduleServiceImplementation(IScheduleRepository iScheduleRepository, IShiftsOptionsRepository iShiftsOptionsRepository, UserRepository userRepository) {
        this.iScheduleRepository = iScheduleRepository;
        this.iShiftsOptionsRepository = iShiftsOptionsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void submitShiftsToSchedule(ScheduleShifts shifts) {
        iScheduleRepository.save(shifts);
        log.info("IN ScheduleServiceImplementation.submitShiftsToSchedule, shift {} ,was added to schedule",shifts);
    }

    @Override
    public void submitShiftsToSchedule(List<ScheduleShifts> shifts) {
        shifts.forEach(iScheduleRepository::save);
        log.info("IN ScheduleServiceImplementation.submitShiftsToSchedule, list of shifts was added to schedule");

    }

    @Override
    public List<ShiftsOptions> getAll() {
        List<ShiftsOptions> allShiftsOptions = iShiftsOptionsRepository.findAll();
        log.info("IN ScheduleServiceImplementation.getAll - {} shifts options found", allShiftsOptions);
        return allShiftsOptions;    }
}
