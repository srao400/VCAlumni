/*Copyright (c) 2018-2019 rubaya.io All Rights Reserved.
 This software is the confidential and proprietary information of rubaya.io You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with rubaya.io*/
package com.vcalumni.vcadb_users.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.export.ExportOptions;

import com.vcalumni.vcadb_users.models.query.*;

public interface VCADB_usersQueryExecutorService {

    Page<GetuserResponse> executeGetuser(String email, Pageable pageable);

    void exportGetuser(String email, ExportOptions exportOptions, Pageable pageable, OutputStream outputStream);

}