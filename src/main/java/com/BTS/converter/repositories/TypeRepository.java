/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.repositories;

import com.BTS.converter.entities.CorporateType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Reza
 */
public interface TypeRepository extends CrudRepository<CorporateType, String>{
 
    @Query (value = "SELECT * FROM CONVERT_M_CORPORATE_TYPE WHERE id=?1", nativeQuery = true)
    public CorporateType getById(String id);
}
