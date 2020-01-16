/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.repositories;

import com.BTS.converter.entities.DetailData;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Reza
 */
@Repository
public interface DetailDataRepository extends CrudRepository<DetailData, Integer> {

   
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE table CONVERT_TR_DETAIL_DATA", nativeQuery = true)
    public void deleteDetailData();

    @Query(value = "select max(ID+1) from CONVERT_TR_DETAIL_DATA", nativeQuery = true)
    public Integer loopId();
    
    @Query(value = "SELECT * FROM CONVERT_TR_DETAIL_DATA WHERE FILE_NAME=?1", nativeQuery = true)
    public List<DetailData> getByName(String file_name);
    
    @Query(value = "SELECT * FROM CONVERT_TR_DETAIL_DATA WHERE FILE_NAME=?1", nativeQuery = true)
    public DetailData getByName2(String file_name);
    
    @Query(value = "SELECT DISTINCT FILE_NAME FROM CONVERT_TR_DETAIL_DATA", nativeQuery = true)
    public List<String> getFileName();
    
    @Query(value = "select max(ID+1) from CONVERT_TR_DETAIL_DATA", nativeQuery = true)
    public Integer loopIdDetail();
  
}
