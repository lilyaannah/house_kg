package boosterschool.realestatesearchservice.services.impl.object;

import boosterschool.realestatesearchservice.models.object.Series;
import boosterschool.realestatesearchservice.repositories.object.SeriesRepo;
import boosterschool.realestatesearchservice.services.MainHouseKgService;
import boosterschool.realestatesearchservice.services.object.SeriesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepo seriesRepo;

    @Override
    public List<String> getDropDownList(){
        List<Series> seriesList=seriesRepo.findAll();
        return seriesList.stream()
                .filter(Series::isActive)
                .map(Series::getSeries)
                .collect(Collectors.toList());
    }

    @Override
    public Series getSeriesById(Integer id) {
        return seriesRepo.getReferenceById(id);
    }
}
