package repository;

import entity.BuildingEntity;
import paging.Pageable;

import java.util.List;
import java.util.Map;

public interface IBuildingRepository extends JpaRepository<BuildingEntity> {
    List<BuildingEntity> findAll(Map<String, Object> params, Pageable pageable);

}
