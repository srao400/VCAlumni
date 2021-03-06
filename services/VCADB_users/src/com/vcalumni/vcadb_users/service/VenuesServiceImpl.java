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
import org.springframework.context.annotation.Lazy;
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
import com.vcalumni.vcadb_users.Venues;


/**
 * ServiceImpl object for domain model class Venues.
 *
 * @see Venues
 */
@Service("VCADB_users.VenuesService")
@Validated
public class VenuesServiceImpl implements VenuesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VenuesServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("VCADB_users.EventsService")
    private EventsService eventsService;

    @Autowired
    @Qualifier("VCADB_users.VenuesDao")
    private WMGenericDao<Venues, String> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Venues, String> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "VCADB_usersTransactionManager")
    @Override
    public Venues create(Venues venues) {
        LOGGER.debug("Creating a new Venues with information: {}", venues);

        Venues venuesCreated = this.wmGenericDao.create(venues);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(venuesCreated);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Venues getById(String venuesId) {
        LOGGER.debug("Finding Venues by id: {}", venuesId);
        return this.wmGenericDao.findById(venuesId);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Venues findById(String venuesId) {
        LOGGER.debug("Finding Venues by id: {}", venuesId);
        try {
            return this.wmGenericDao.findById(venuesId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Venues found with id: {}", venuesId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public List<Venues> findByMultipleIds(List<String> venuesIds, boolean orderedReturn) {
        LOGGER.debug("Finding Venues by ids: {}", venuesIds);

        return this.wmGenericDao.findByMultipleIds(venuesIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "VCADB_usersTransactionManager")
    @Override
    public Venues update(Venues venues) {
        LOGGER.debug("Updating Venues with information: {}", venues);

        this.wmGenericDao.update(venues);
        this.wmGenericDao.refresh(venues);

        return venues;
    }

    @Transactional(value = "VCADB_usersTransactionManager")
    @Override
    public Venues delete(String venuesId) {
        LOGGER.debug("Deleting Venues with id: {}", venuesId);
        Venues deleted = this.wmGenericDao.findById(venuesId);
        if (deleted == null) {
            LOGGER.debug("No Venues found with id: {}", venuesId);
            throw new EntityNotFoundException(String.valueOf(venuesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "VCADB_usersTransactionManager")
    @Override
    public void delete(Venues venues) {
        LOGGER.debug("Deleting Venues with {}", venues);
        this.wmGenericDao.delete(venues);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Page<Venues> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Venues");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Page<Venues> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Venues");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service VCADB_users for table Venues to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service VCADB_users for table Venues to {} format", options.getExportType());
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

    @Transactional(readOnly = true, value = "VCADB_usersTransactionManager")
    @Override
    public Page<Events> findAssociatedEventses(String venuename, Pageable pageable) {
        LOGGER.debug("Fetching all associated eventses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("venues.venuename = '" + venuename + "'");

        return eventsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service EventsService instance
     */
    protected void setEventsService(EventsService service) {
        this.eventsService = service;
    }

}