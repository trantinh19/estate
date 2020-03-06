package service.impl;


import builder.BuildingSearchBuilder;
import converter.BuildingConverter;
import dto.BuildingDTO;
import paging.Pageable;
import repository.IBuildingRepository;
import repository.impl.BuildingRepository;
import service.IBuildingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuildingService implements IBuildingService {

    private IBuildingRepository buildingRepository;
    //    private ModelMapperUtils modelMapperUtils;
    private BuildingConverter buildingConverter;

    public BuildingService() {
        this.buildingRepository = new BuildingRepository();
        this.buildingConverter = new BuildingConverter();
//        this.modelMapperUtils = new ModelMapperUtils();
    }

    @Override
    public List<BuildingDTO> findAll(BuildingSearchBuilder fieldSearch, Pageable pageable) {

        /*java 7*/
//        List<BuildingDTO> results = new ArrayList<>();
//        List<BuildingEntity> buildingEntities = buildingRepository.findAll();
//        for (BuildingEntity item : buildingEntities) {
//            BuildingDTO buildingDTO = new BuildingDTO();
//            buildingDTO.setName(item.getName());
//            buildingDTO.setStructure(item.getStructure());
//            results.add(buildingDTO);
//        }

//        for (BuildingEntity item : buildingEntities) {
//            BuildingDTO buildingDTO = buildingConverter.convertToDTO(item);
////            results.add(buildingDTO);
////        }
//        return results;

        /*java 8*/
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", fieldSearch.getName());
        properties.put("district", fieldSearch.getDistrict());
        properties.put("buildingArea", fieldSearch.getBuildingArea());
        properties.put("numberOfBasement", fieldSearch.getNumberOfBasement());

        return buildingRepository
                .findAll(properties, pageable)
                .stream()
                .map(item -> buildingConverter.convertToDTO(item))
                .collect(Collectors.toList());
    }

}
