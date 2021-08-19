package uk.tw.energy.service;

import org.springframework.stereotype.Service;
import uk.tw.energy.domain.ElectricityReading;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MeterReadingService {

    private final Map<String, List<ElectricityReading>> meterAssociatedReadings;

    public MeterReadingService(Map<String, List<ElectricityReading>> meterAssociatedReadings) {
        this.meterAssociatedReadings = meterAssociatedReadings;
    }

    public Optional<List<ElectricityReading>> getReadings(String smartMeterId) {
        return Optional.ofNullable(meterAssociatedReadings.get(smartMeterId));
    }
    public Optional<List<ElectricityReading>> getReadingsAfterDate(String smartMeterId, Instant date) {
        Optional<List<ElectricityReading>> allReadings = Optional.ofNullable(meterAssociatedReadings.get(smartMeterId));
        return Optional.ofNullable(
                allReadings.get().stream()
                .filter(r -> r.getTime().isAfter(date))
                .collect(Collectors.toList())
        );
    }

    public void storeReadings(String smartMeterId, List<ElectricityReading> electricityReadings) {
        if (!meterAssociatedReadings.containsKey(smartMeterId)) {
            meterAssociatedReadings.put(smartMeterId, new ArrayList<>());
        }
        meterAssociatedReadings.get(smartMeterId).addAll(electricityReadings);
    }
}
