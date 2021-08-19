package uk.tw.energy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.domain.PricePlan;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class PricePlanServiceTest {


    private static final String PRICE_PLAN_ID = "price-plan-id";
    private static final String SMART_METER_ID = "smart-meter-id";
    private static final String SMART_METER_ID_2 = "smart-meter-id_2";

    private static final BigDecimal PRICE = BigDecimal.valueOf(2l);

    private AccountService accountService;
    private MeterReadingService meterReadingService;
    private PricePlanService pricePlanService;

    @BeforeEach
    public void setUp() {
        Map<String, String> smartMeterToPricePlanAccounts = new HashMap<>();
        smartMeterToPricePlanAccounts.put(SMART_METER_ID, PRICE_PLAN_ID);
        smartMeterToPricePlanAccounts.put(SMART_METER_ID_2, PRICE_PLAN_ID);

        accountService = new AccountService(smartMeterToPricePlanAccounts);

        meterReadingService = new MeterReadingService(new HashMap<>());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -4);
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DATE, -6);
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DATE, -8);

        meterReadingService.storeReadings(SMART_METER_ID,
                Arrays.asList(new ElectricityReading(cal.toInstant(), BigDecimal.ONE), new ElectricityReading(cal1.toInstant(), BigDecimal.ONE), new ElectricityReading(cal2.toInstant(), BigDecimal.TEN)));

        meterReadingService.storeReadings(SMART_METER_ID_2,
                Arrays.asList(new ElectricityReading(cal2.toInstant(), BigDecimal.TEN)));

        List<PricePlan> pricePlans = Arrays.asList( new PricePlan(PRICE_PLAN_ID, null, PRICE, null));

        pricePlanService = new PricePlanService(pricePlans, meterReadingService, accountService);
    }

    @Test
    public void consumptionCostOfElectricityReadingsFromPreviousWeekShouldReturnValue() {
        // GIVEN

        // WHEN
        Optional<BigDecimal> result = pricePlanService.getConsumptionCostOfElectricityReadingsFromPreviousWeek(SMART_METER_ID);

        // THEN
        BigDecimal expectedResult = PRICE.multiply(BigDecimal.valueOf(96l));
        Assert.

    }

}


