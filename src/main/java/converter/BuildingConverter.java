package converter;

import dto.BuildingDTO;
import entity.BuildingEntity;
import org.modelmapper.ModelMapper;

public class BuildingConverter {
    public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(buildingEntity, BuildingDTO.class);
    }
}
