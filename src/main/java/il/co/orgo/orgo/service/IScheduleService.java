package il.co.orgo.orgo.service;

import il.co.orgo.orgo.model.ScheduleShifts;
import il.co.orgo.orgo.model.ShiftsOptions;
import il.co.orgo.orgo.model.User;

import java.util.List;

public interface IScheduleService {

    void submitShiftsToSchedule(ScheduleShifts shifts);
    void submitShiftsToSchedule(List<ScheduleShifts> shifts);
    List<ShiftsOptions> getAll();
}
