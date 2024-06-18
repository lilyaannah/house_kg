package boosterschool.realestatesearchservice.services.impl.money;

import boosterschool.realestatesearchservice.models.money.Price;
import boosterschool.realestatesearchservice.repositories.money.PriceRepo;
import boosterschool.realestatesearchservice.services.money.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final PriceRepo priceRepo;
    @Override
    public Price save(Price price) {
        return priceRepo.save(price);
    }
}
