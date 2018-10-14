/*Copyright (c) 2018-2019 rubaya.io All Rights Reserved.
 This software is the confidential and proprietary information of rubaya.io You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with rubaya.io*/
package com.vcalumni.vcadb_users;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Venues generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`venues`")
public class Venues implements Serializable {

    private String venuename;
    private String venuedetails;
    private String addressline1;
    private String addressline2;
    private String city;
    private String province;
    private String zipcode;
    private String country;
    private String phone;

    @Id
    @Column(name = "`venuename`", nullable = false, length = 40)
    public String getVenuename() {
        return this.venuename;
    }

    public void setVenuename(String venuename) {
        this.venuename = venuename;
    }

    @Column(name = "`venuedetails`", nullable = false, length = 255)
    public String getVenuedetails() {
        return this.venuedetails;
    }

    public void setVenuedetails(String venuedetails) {
        this.venuedetails = venuedetails;
    }

    @Column(name = "`addressline1`", nullable = false, length = 255)
    public String getAddressline1() {
        return this.addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    @Column(name = "`addressline2`", nullable = true, length = 255)
    public String getAddressline2() {
        return this.addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    @Column(name = "`city`", nullable = false, length = 255)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "`province`", nullable = false, length = 255)
    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "`zipcode`", nullable = false, length = 255)
    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Column(name = "`country`", nullable = false, length = 255)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "`phone`", nullable = true, length = 255)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venues)) return false;
        final Venues venues = (Venues) o;
        return Objects.equals(getVenuename(), venues.getVenuename());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVenuename());
    }
}