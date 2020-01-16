/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.repositories;

import com.BTS.converter.entities.ClientPartner;
import com.BTS.converter.entities.HistoryFile;
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
public interface HistoryFileRepository extends CrudRepository<HistoryFile, String>{
    @Query(value="select * from CONVERT_M_HISTORY_FILE where NEW_FILENAME=?1", nativeQuery = true)
    public HistoryFile getByFilename(String filename);

    @Query(value="select * from CONVERT_M_HISTORY_FILE where OLD_FILENAME=?1", nativeQuery = true)
    public HistoryFile getByOldFilename(String filename);
    
    @Query(value = "select max(COUNT+1) from CONVERT_M_HISTORY_FILE where client=?1",nativeQuery = true)
    public Integer loopCount(String client);
    
    @Query(value="select * from CONVERT_M_HISTORY_FILE where CLIENT=?1", nativeQuery = true)
    public HistoryFile getByClient(String client);
    
    @Query(value ="select max(ID+1) from CONVERT_M_HISTORY_FILE", nativeQuery = true)
    public Integer loopIdHistory();
}
