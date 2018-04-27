package com.seprid.lineservice;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LineObjectRepository extends CrudRepository<LineObject, Integer> {
    List<LineObject> findByContainerName(String name);

    LineObject findDistinctTopByContainerNameNotNull();

}
