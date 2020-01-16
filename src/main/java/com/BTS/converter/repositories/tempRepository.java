/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.repositories;

import com.BTS.converter.entities.Temp;
import java.math.BigDecimal;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public interface tempRepository extends CrudRepository<Temp, Integer> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE table CONVERT_TEMP", nativeQuery = true)
    public void deleteTemp();

    @Query(value = "select max(ID+1) from CONVERT_TEMP", nativeQuery = true)
    public Integer loopId();

    @Query(value = "SELECT * FROM CONVERT_TEMP WHERE File_Name=?1", nativeQuery = true)
    public List<Temp> getByName(String file_name);
    
    @Query(value = "SELECT * FROM CONVERT_TEMP WHERE client=?1", nativeQuery = true)
    public List<Temp> getByClient(Integer client);

    @Query(value = "SELECT DISTINCT CLIENT FROM CONVERT_TEMP", nativeQuery = true)
    public List<BigDecimal> clientId();
    
    @Query(value = "SELECT DISTINCT FILE_NAME FROM CONVERT_TEMP WHERE CLIENT =?1", nativeQuery = true)
    public List<String> FileName(BigDecimal client);
    
    @Query(value = "select max(ID+1) from CONVERT_TEMP", nativeQuery = true)
    public Integer loopIdTemp();
}
