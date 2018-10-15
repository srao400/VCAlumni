/*Copyright (c) 2018-2019 rubaya.io All Rights Reserved.
 This software is the confidential and proprietary information of rubaya.io You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with rubaya.io*/
package com.vcalumni.vcadb_users.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.IntegerWrapper;
import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.data.export.ExportOptions;
import com.wavemaker.runtime.file.manager.ExportedFileManager;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import com.vcalumni.vcadb_users.service.VCADB_usersQueryExecutorService;
import com.vcalumni.vcadb_users.models.query.*;

@RestController(value = "VCADB_users.QueryExecutionController")
@RequestMapping("/VCADB_users/queryExecutor")
@Api(value = "QueryExecutionController", description = "controller class for query execution")
public class QueryExecutionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryExecutionController.class);

    @Autowired
    private VCADB_usersQueryExecutorService queryService;

    @Autowired
	private ExportedFileManager exportedFileManager;

    @RequestMapping(value = "/queries/RegisteredAttendees", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "People registered for an event")
    public Page<RegisteredAttendeesResponse> executeRegisteredAttendees(@RequestParam(value = "event_id") String eventId, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: RegisteredAttendees");
        Page<RegisteredAttendeesResponse> _result = queryService.executeRegisteredAttendees(eventId, pageable);
        LOGGER.debug("got the result for named query: RegisteredAttendees, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query RegisteredAttendees")
    @RequestMapping(value = "/queries/RegisteredAttendees/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportRegisteredAttendees(@RequestParam(value = "event_id") String eventId, @RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: RegisteredAttendees");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "RegisteredAttendees";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportRegisteredAttendees(eventId,  exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

    @RequestMapping(value = "/queries/EventsList", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "List of events")
    public Page<EventsListResponse> executeEventsList(@RequestParam(value = "eventtype") String eventtype, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: EventsList");
        Page<EventsListResponse> _result = queryService.executeEventsList(eventtype, pageable);
        LOGGER.debug("got the result for named query: EventsList, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query EventsList")
    @RequestMapping(value = "/queries/EventsList/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportEventsList(@RequestParam(value = "eventtype") String eventtype, @RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: EventsList");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "EventsList";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportEventsList(eventtype,  exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

    @RequestMapping(value = "/queries/AttendeeEventDetails", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Events for attendees")
    public Page<AttendeeEventDetailsResponse> executeAttendeeEventDetails(@RequestParam(value = "email") String email, @RequestParam(value = "eventname") String eventname, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: AttendeeEventDetails");
        Page<AttendeeEventDetailsResponse> _result = queryService.executeAttendeeEventDetails(email, eventname, pageable);
        LOGGER.debug("got the result for named query: AttendeeEventDetails, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query AttendeeEventDetails")
    @RequestMapping(value = "/queries/AttendeeEventDetails/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportAttendeeEventDetails(@RequestParam(value = "email") String email, @RequestParam(value = "eventname") String eventname, @RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: AttendeeEventDetails");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "AttendeeEventDetails";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportAttendeeEventDetails(email, eventname,  exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

    @RequestMapping(value = "/queries/getuser", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Get User")
    public Page<GetuserResponse> executeGetuser(@RequestParam(value = "email") String email, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: getuser");
        Page<GetuserResponse> _result = queryService.executeGetuser(email, pageable);
        LOGGER.debug("got the result for named query: getuser, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query getuser")
    @RequestMapping(value = "/queries/getuser/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportGetuser(@RequestParam(value = "email") String email, @RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: getuser");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "getuser";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportGetuser(email,  exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

    @RequestMapping(value = "/queries/EventParticipants", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Event participants")
    public Page<EventParticipantsResponse> executeEventParticipants(@RequestParam(value = "eventname") String eventname, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: EventParticipants");
        Page<EventParticipantsResponse> _result = queryService.executeEventParticipants(eventname, pageable);
        LOGGER.debug("got the result for named query: EventParticipants, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query EventParticipants")
    @RequestMapping(value = "/queries/EventParticipants/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportEventParticipants(@RequestParam(value = "eventname") String eventname, @RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: EventParticipants");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "EventParticipants";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportEventParticipants(eventname,  exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

    @RequestMapping(value = "/queries/UpdateAttendees", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Update Attendees")
    public IntegerWrapper executeUpdateAttendees(@Valid @RequestBody UpdateAttendeesRequest updateAttendeesRequest, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: UpdateAttendees");
        Integer _result = queryService.executeUpdateAttendees(updateAttendeesRequest);
        LOGGER.debug("got the result for named query: UpdateAttendees, result:{}", _result);
        return new IntegerWrapper(_result);
    }

    @RequestMapping(value = "/queries/EventDetails", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Event Details")
    public Page<EventDetailsResponse> executeEventDetails(@RequestParam(value = "event_id") String eventId, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: EventDetails");
        Page<EventDetailsResponse> _result = queryService.executeEventDetails(eventId, pageable);
        LOGGER.debug("got the result for named query: EventDetails, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query EventDetails")
    @RequestMapping(value = "/queries/EventDetails/export", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportEventDetails(@RequestParam(value = "event_id") String eventId, @RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: EventDetails");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "EventDetails";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportEventDetails(eventId,  exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

}