package com.seprid.lineservice;

import org.springframework.data.repository.CrudRepository;

public interface LineObjectRepository extends CrudRepository<LineObject, Integer> {
    Iterable<LineObject> findByContainerName(String name);

    Iterable<LineObject> findDistinctTopByContainerNameNotNull();

}
