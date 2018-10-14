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

import com.vcalumni.vcadb_users.Events;


/**
 * ServiceImpl object for domain model class Events.
 *
 * @see Events
 */
@Service("VCADB_users.EventsService")
@Validated
public class EventsServiceImpl implements EventsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsServiceImpl.class);


    @Autowired
    @Qualifier("VCADB_users.EventsDao")
    private WMGenericDao<Events, String> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Events, String> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "VCADB_usersTransactionManager")
    @Override
    public Events create(Events events) {
        LOGGER.debug("Creating a new Events with information: {}", events);

        Events eventsCreated = this.wmGenericDao.create(events);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(eventsCreated);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Events getById(String eventsId) {
        LOGGER.debug("Finding Events by id: {}", eventsId);
        return this.wmGenericDao.findById(eventsId);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Events findById(String eventsId) {
        LOGGER.debug("Finding Events by id: {}", eventsId);
        try {
            return this.wmGenericDao.findById(eventsId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Events found with id: {}", eventsId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public List<Events> findByMultipleIds(List<String> eventsIds, boolean orderedReturn) {
        LOGGER.debug("Finding Events by ids: {}", eventsIds);

        return this.wmGenericDao.findByMultipleIds(eventsIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "VCADB_usersTransactionManager")
    @Override
    public Events update(Events events) {
        LOGGER.debug("Updating Events with information: {}", events);

        this.wmGenericDao.update(events);
        this.wmGenericDao.refresh(events);

        return events;
    }

    @Transactional(value = "VCADB_usersTransactionManager")
    @Override
    public Events delete(String eventsId) {
        LOGGER.debug("Deleting Events with id: {}", eventsId);
        Events deleted = this.wmGenericDao.findById(eventsId);
        if (deleted == null) {
            LOGGER.debug("No Events found with id: {}", eventsId);
            throw new EntityNotFoundException(String.valueOf(eventsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "VCADB_usersTransactionManager")
    @Override
    public void delete(Events events) {
        LOGGER.debug("Deleting Events with {}", events);
        this.wmGenericDao.delete(events);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Page<Events> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Events");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Page<Events> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Events");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service VCADB_users for table Events to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service VCADB_users for table Events to {} format", options.getExportType());
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