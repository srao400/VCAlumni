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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import com.vcalumni.vcadb_users.models.procedure.AddAttendeeRequest;
import com.vcalumni.vcadb_users.service.VCADB_usersProcedureExecutorService;

@RestController(value = "VCADB_users.ProcedureExecutionController")
@RequestMapping("/VCADB_users/procedureExecutor")
@Api(value = "ProcedureExecutionController", description = "controller class for procedure execution")
public class ProcedureExecutionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcedureExecutionController.class);

    @Autowired
    private VCADB_usersProcedureExecutorService procedureService;

    @RequestMapping(value = "/procedure/execute/add_attendee", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Add attendee")
    public Void executeAdd_attendee(@Valid @RequestBody AddAttendeeRequest addAttendeeRequest, HttpServletRequest _request) {
        LOGGER.debug("Executing named procedure: add_attendee");
        Void _result = procedureService.executeAdd_attendee(addAttendeeRequest);
        LOGGER.debug("got the result for named procedure: add_attendee, result:{}", _result);
        return _result;
    }

    @RequestMapping(value = "/procedure/execute/change_attendee", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Change Attendee status")
    public Void executeChange_attendee(@RequestParam(value = "email_id") String emailId, @RequestParam(value = "event_id") String eventId, @RequestParam(value = "action_id") String actionId, HttpServletRequest _request) {
        LOGGER.debug("Executing named procedure: change_attendee");
        Void _result = procedureService.executeChange_attendee(emailId, eventId, actionId);
        LOGGER.debug("got the result for named procedure: change_attendee, result:{}", _result);
        return _result;
    }

}