package repository;

import paging.Pageable;

import java.util.List;
import java.util.Map;

public interface JpaRepository<T> {
    List<T> findAll(Map<String, Object> properties, Pageable pageable, Object... objects);
}
