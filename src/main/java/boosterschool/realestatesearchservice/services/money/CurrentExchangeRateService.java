package boosterschool.realestatesearchservice.services.money;

import boosterschool.realestatesearchservice.models.money.CurrentExchangeRate;

public interface CurrentExchangeRateService {
    public CurrentExchangeRate getCurrentExchangeRate();
    public CurrentExchangeRate updateExchangeRate(double newRate);
}
