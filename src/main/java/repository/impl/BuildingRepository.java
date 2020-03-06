package repository.impl;

import entity.BuildingEntity;
import paging.Pageable;
import repository.IBuildingRepository;

import java.util.List;
import java.util.Map;

public class BuildingRepository extends SimpleJpaRepository<BuildingEntity> implements IBuildingRepository {


    @Override
    public List<BuildingEntity> findAll(Map<String, Object> params, Pageable pageable) {
        return this.findAll(params, pageable);
    }
}
