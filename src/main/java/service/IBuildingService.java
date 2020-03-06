package service;


import builder.BuildingSearchBuilder;
import dto.BuildingDTO;
import paging.Pageable;

import java.util.List;

public interface IBuildingService {
    List<BuildingDTO> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable);
}
