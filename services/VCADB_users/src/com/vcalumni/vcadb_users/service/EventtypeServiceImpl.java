/*Copyright (c) 2018-2019 rubaya.io All Rights Reserved.
 This software is the confidential and proprietary information of rubaya.io You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with rubaya.io*/
package com.vcalumni.vcadb_users.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.vcalumni.vcadb_users.Eventtype;


/**
 * ServiceImpl object for domain model class Eventtype.
 *
 * @see Eventtype
 */
@Service("VCADB_users.EventtypeService")
@Validated
public class EventtypeServiceImpl implements EventtypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventtypeServiceImpl.class);


    @Autowired
    @Qualifier("VCADB_users.EventtypeDao")
    private WMGenericDao<Eventtype, String> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Eventtype, String> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "VCADB_usersTransactionManager")
    @Override
    public Eventtype create(Eventtype eventtypeInstance) {
        LOGGER.debug("Creating a new Eventtype with information: {}", eventtypeInstance);

        Eventtype eventtypeInstanceCreated = this.wmGenericDao.create(eventtypeInstance);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(eventtypeInstanceCreated);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Eventtype getById(String eventtypeId) {
        LOGGER.debug("Finding Eventtype by id: {}", eventtypeId);
        return this.wmGenericDao.findById(eventtypeId);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Eventtype findById(String eventtypeId) {
        LOGGER.debug("Finding Eventtype by id: {}", eventtypeId);
        try {
            return this.wmGenericDao.findById(eventtypeId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Eventtype found with id: {}", eventtypeId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public List<Eventtype> findByMultipleIds(List<String> eventtypeIds, boolean orderedReturn) {
        LOGGER.debug("Finding Eventtypes by ids: {}", eventtypeIds);

        return this.wmGenericDao.findByMultipleIds(eventtypeIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "VCADB_usersTransactionManager")
    @Override
    public Eventtype update(Eventtype eventtypeInstance) {
        LOGGER.debug("Updating Eventtype with information: {}", eventtypeInstance);

        this.wmGenericDao.update(eventtypeInstance);
        this.wmGenericDao.refresh(eventtypeInstance);

        return eventtypeInstance;
    }

    @Transactional(value = "VCADB_usersTransactionManager")
    @Override
    public Eventtype delete(String eventtypeId) {
        LOGGER.debug("Deleting Eventtype with id: {}", eventtypeId);
        Eventtype deleted = this.wmGenericDao.findById(eventtypeId);
        if (deleted == null) {
            LOGGER.debug("No Eventtype found with id: {}", eventtypeId);
            throw new EntityNotFoundException(String.valueOf(eventtypeId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "VCADB_usersTransactionManager")
    @Override
    public void delete(Eventtype eventtypeInstance) {
        LOGGER.debug("Deleting Eventtype with {}", eventtypeInstance);
        this.wmGenericDao.delete(eventtypeInstance);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Page<Eventtype> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Eventtypes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Page<Eventtype> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Eventtypes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service VCADB_users for table Eventtype to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service VCADB_users for table Eventtype to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}