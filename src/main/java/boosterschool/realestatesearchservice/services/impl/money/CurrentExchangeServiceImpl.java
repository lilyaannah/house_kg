package boosterschool.realestatesearchservice.services.impl.money;

import boosterschool.realestatesearchservice.models.money.CurrentExchangeRate;
import boosterschool.realestatesearchservice.repositories.money.CurrentExchangeRateRepo;
import boosterschool.realestatesearchservice.services.money.CurrentExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CurrentExchangeServiceImpl implements CurrentExchangeRateService {
    private final CurrentExchangeRateRepo exchangeRateRepository;

    @Override
    public CurrentExchangeRate getCurrentExchangeRate() {
        Optional<CurrentExchangeRate> currentRateOpt = exchangeRateRepository.findTopByOrderByStartDateDesc();
        return currentRateOpt.orElse(null);
    }

    @Override
    public CurrentExchangeRate updateExchangeRate(double newRate) {
        CurrentExchangeRate currentRate = getCurrentExchangeRate();
        LocalDateTime now = LocalDateTime.now();

        if (currentRate != null) {
            currentRate.setEndDate(now);
            exchangeRateRepository.save(currentRate);
        }

        CurrentExchangeRate newExchangeRate = new CurrentExchangeRate();
        newExchangeRate.setExchangeRate(newRate);
        newExchangeRate.setStartDate(now);
        newExchangeRate.setEndDate(null);

        return exchangeRateRepository.save(newExchangeRate);
    }
}

