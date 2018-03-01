/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.device;

import com.sitewhere.spi.common.IMetadataProviderEntity;
import com.sitewhere.spi.device.element.IDeviceElementSchema;

/**
 * Specifies details about a given type of device.
 * 
 * @author Derek
 */
public interface IDeviceType extends IMetadataProviderEntity {

    /**
     * Get name.
     * 
     * @return
     */
    public String getName();

    /**
     * Get description.
     * 
     * @return
     */
    public String getDescription();

    /**
     * Get URL for image.
     * 
     * @return
     */
    public String getImageUrl();

    /**
     * Indicates whether this device contains other devices.
     * 
     * @return
     */
    public DeviceContainerPolicy getContainerPolicy();

    /**
     * Get schema that describes how nested devices are arranged.
     * 
     * @return
     */
    public IDeviceElementSchema getDeviceElementSchema();
}