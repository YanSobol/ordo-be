package il.co.orgo.orgo.service;

import il.co.orgo.orgo.model.ShiftsOptions;

import java.util.List;

public interface IShiftsOptionsService {

    void submitUserShiftsOptions(ShiftsOptions options);
    void submitUserShiftsOptions(List<ShiftsOptions> options);
    List<ShiftsOptions> getAll();
}
