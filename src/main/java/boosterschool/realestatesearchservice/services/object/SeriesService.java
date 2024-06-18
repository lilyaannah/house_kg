package boosterschool.realestatesearchservice.services.object;

import boosterschool.realestatesearchservice.models.object.Series;

import java.util.List;

public interface SeriesService {
    Series getSeriesById(Integer id);
    List<String> getDropDownList();
}
