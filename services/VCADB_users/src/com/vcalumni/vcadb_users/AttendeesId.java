/*Copyright (c) 2018-2019 rubaya.io All Rights Reserved.
 This software is the confidential and proprietary information of rubaya.io You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with rubaya.io*/
package com.vcalumni.vcadb_users;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

public class AttendeesId implements Serializable {

    private String email;
    private String eventname;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEventname() {
        return this.eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attendees)) return false;
        final Attendees attendees = (Attendees) o;
        return Objects.equals(getEmail(), attendees.getEmail()) &&
                Objects.equals(getEventname(), attendees.getEventname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(),
                getEventname());
    }
}