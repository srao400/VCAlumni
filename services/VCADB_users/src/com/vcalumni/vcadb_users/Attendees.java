/*Copyright (c) 2018-2019 rubaya.io All Rights Reserved.
 This software is the confidential and proprietary information of rubaya.io You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with rubaya.io*/
package com.vcalumni.vcadb_users;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Attendees generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`attendees`")
@IdClass(AttendeesId.class)
public class Attendees implements Serializable {

    private String email;
    private String eventname;
    private Boolean status;
    private Boolean attended;
    private Integer _count;
    private Users users;

    @Id
    @Column(name = "`email`", nullable = false, length = 255)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @Column(name = "`eventname`", nullable = false, length = 60)
    public String getEventname() {
        return this.eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    @Column(name = "`status`", nullable = true)
    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Column(name = "`attended`", nullable = true)
    public Boolean getAttended() {
        return this.attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    @Column(name = "`count`", nullable = true, scale = 0, precision = 10)
    public Integer get_count() {
        return this._count;
    }

    public void set_count(Integer _count) {
        this._count = _count;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`email`", referencedColumnName = "`email`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_users_TO_attendees_emJZwe2`"))
    @Fetch(FetchMode.JOIN)
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        if(users != null) {
            this.email = users.getEmail();
        }

        this.users = users;
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