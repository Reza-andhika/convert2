/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.repositories;

import com.BTS.converter.entities.Parameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Reza
 */
@Repository
public interface ParameterRepository extends CrudRepository<Parameter, Integer> {

    @Query(value = "select max(ID+1) from CONVERT_M_PARAMETER", nativeQuery = true)
    public Integer loopIdParameter();
}
