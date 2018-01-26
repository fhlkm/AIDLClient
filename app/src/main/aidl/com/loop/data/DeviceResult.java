/**
 * Copyright (C) 2018 Samsung Electronics Co., Ltd. All rights reserved.
 * <p/>
 * Mobile Communication Division,
 * Digital Media & Communications Business, Samsung Electronics Co., Ltd.
 * <p/>
 * This software and its documentation are confidential and proprietary
 * information of Samsung Electronics Co., Ltd.  No part of the software and
 * documents may be copied, reproduced, transmitted, translated, or reduced to
 * any electronic medium or machine-readable form without the prior written
 * consent of Samsung Electronics.
 * <p/>
 * Samsung Electronics makes no representations with respect to the contents,
 * and assumes no responsibility for any errors that might appear in the
 * software and documents. This publication and the contents hereof are subject
 * to change without notice.
 */

package com.loop.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;

import java.util.Locale;

public class DeviceResult implements Parcelable{
    /**
     * The Id.
     */
    private String id;
    /**
     * The Type.
     */
    private String type;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Imei.
     */
    private String imei;
    /**
     * The Mac.
     */
    private String mac;
    /**
     * The Serial.
     */
    private String serial;
    /**
     * The Msisdn.
     */
    private String msisdn;
    /**
     * The Ip.
     */
    private String ip;
    /**
     * The Model.
     */
    private String model;
    /**
     * The Manufacturer.
     */
    private String manufacturer;
    /**
     * The Brand.
     */
    private String brand;
    /**
     * The Product.
     */
    private String product;
    /**
     * The Data.
     */
    private Object data;
    /**
     * The Locale.
     */
    private Locale locale;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public DeviceResult() {
    }


    public DeviceResult(Parcel in) {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(JSON.toJSONString(DeviceResult.this));
    }

    public static final Creator<DeviceResult> CREATOR = new Creator<DeviceResult>() {
        @Override
        public DeviceResult createFromParcel(Parcel source) {
            String m_DeviceResult = source.readString();
            if(null != m_DeviceResult&& !m_DeviceResult.isEmpty()) {
                DeviceResult mResult =   JSON.parseObject(m_DeviceResult, DeviceResult.class);
                return mResult;
            }
            return null;
        }

        @Override
        public DeviceResult[] newArray(int size) {
            return new DeviceResult[size];
        }
    };
}
