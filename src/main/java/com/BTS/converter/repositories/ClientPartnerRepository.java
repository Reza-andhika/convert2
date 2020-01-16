/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BTS.converter.repositories;

import com.BTS.converter.entities.ClientPartner;
import java.nio.file.Path;
import java.util.List;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Reza
 */
@Repository
@Transactional
@Component
public interface ClientPartnerRepository extends CrudRepository<ClientPartner, Integer> {

    @Query(value = "select * from CONVERT_M_CLIENT_PARTNER where ID=?1", nativeQuery = true)
    public ClientPartner getById(String id);

    @Query(value = "select * from CONVERT_M_CLIENT_PARTNER where CLIENT_id=?1", nativeQuery = true)
    public List<ClientPartner> getByExt(String id);

    @Query(value = "SELECT * FROM CONVERT_M_CLIENT_PARTNER WHERE INCOMING_PATH=?1", nativeQuery = true)
    public ClientPartner getByIncoming(String path);

     @Query(value = "select max(ID+1) from CONVERT_M_CLIENT_PARTNER", nativeQuery = true)
    public String loopId();

    @Query(value ="select max(ID+1) from CONVERT_M_CLIENT_PARTNER", nativeQuery = true)
    public Integer loopIdClient();
    
}
