/*Copyright (c) 2018-2019 rubaya.io All Rights Reserved.
 This software is the confidential and proprietary information of rubaya.io You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with rubaya.io*/
package com.vcalumni.vcadb_users.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.vcalumni.vcadb_users.Attendees;
import com.vcalumni.vcadb_users.AttendeesId;
import com.vcalumni.vcadb_users.service.AttendeesService;


/**
 * Controller object for domain model class Attendees.
 * @see Attendees
 */
@RestController("VCADB_users.AttendeesController")
@Api(value = "AttendeesController", description = "Exposes APIs to work with Attendees resource.")
@RequestMapping("/VCADB_users/Attendees")
public class AttendeesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttendeesController.class);

    @Autowired
	@Qualifier("VCADB_users.AttendeesService")
	private AttendeesService attendeesService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new Attendees instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Attendees createAttendees(@RequestBody Attendees attendees) {
		LOGGER.debug("Create Attendees with information: {}" , attendees);

		attendees = attendeesService.create(attendees);
		LOGGER.debug("Created Attendees with information: {}" , attendees);

	    return attendees;
	}

    @ApiOperation(value = "Returns the Attendees instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Attendees getAttendees(@RequestParam("email") String email, @RequestParam("eventname") String eventname) {

        AttendeesId attendeesId = new AttendeesId();
        attendeesId.setEmail(email);
        attendeesId.setEventname(eventname);

        LOGGER.debug("Getting Attendees with id: {}" , attendeesId);
        Attendees attendees = attendeesService.getById(attendeesId);
        LOGGER.debug("Attendees details with id: {}" , attendees);

        return attendees;
    }



    @ApiOperation(value = "Updates the Attendees instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Attendees editAttendees(@RequestParam("email") String email, @RequestParam("eventname") String eventname, @RequestBody Attendees attendees) {

        attendees.setEmail(email);
        attendees.setEventname(eventname);

        LOGGER.debug("Attendees details with id is updated with: {}" , attendees);

        return attendeesService.update(attendees);
    }


    @ApiOperation(value = "Deletes the Attendees instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteAttendees(@RequestParam("email") String email, @RequestParam("eventname") String eventname) {

        AttendeesId attendeesId = new AttendeesId();
        attendeesId.setEmail(email);
        attendeesId.setEventname(eventname);

        LOGGER.debug("Deleting Attendees with id: {}" , attendeesId);
        Attendees attendees = attendeesService.delete(attendeesId);

        return attendees != null;
    }


    /**
     * @deprecated Use {@link #findAttendees(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Attendees instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Attendees> searchAttendeesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Attendees list by query filter:{}", (Object) queryFilters);
        return attendeesService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Attendees instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Attendees> findAttendees(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Attendees list by filter:", query);
        return attendeesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Attendees instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Attendees> filterAttendees(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Attendees list by filter", query);
        return attendeesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportAttendees(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return attendeesService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @RequestMapping(value = "/export", method = {RequestMethod.POST}, consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportAttendeesAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = Attendees.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> attendeesService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of Attendees instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countAttendees( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Attendees");
		return attendeesService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getAttendeesAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return attendeesService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service AttendeesService instance
	 */
	protected void setAttendeesService(AttendeesService service) {
		this.attendeesService = service;
	}

}