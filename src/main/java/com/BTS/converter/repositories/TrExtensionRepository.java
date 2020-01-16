/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.repositories;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.entities.TrExtension;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public interface TrExtensionRepository extends CrudRepository<TrExtension, BigDecimal>{
    
    @Query(value = "SELECT * FROM CONVERT_TR_EXTENSION WHERE CLIENT_ID =?1", nativeQuery = true)
    public List<TrExtension> getByClientId(BigDecimal id);
    
    @Query(value = "select max(ID+1) from CONVERT_TR_EXTENSION", nativeQuery = true)
    public Integer loopIdDetail();
}
