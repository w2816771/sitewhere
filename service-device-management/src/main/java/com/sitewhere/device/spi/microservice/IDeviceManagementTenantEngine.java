/**
 * Copyright © 2014-2021 The SiteWhere Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sitewhere.device.spi.microservice;

import com.sitewhere.device.configuration.DeviceManagementTenantConfiguration;
import com.sitewhere.device.spi.kafka.IDeviceInteractionEventsProducer;
import com.sitewhere.grpc.service.DeviceManagementGrpc;
import com.sitewhere.microservice.api.device.IDeviceManagement;
import com.sitewhere.rdb.spi.IRdbEntityManagerProvider;
import com.sitewhere.spi.microservice.multitenant.IMicroserviceTenantEngine;

/**
 * Extends {@link IMicroserviceTenantEngine} with features specific to device
 * management.
 */
public interface IDeviceManagementTenantEngine extends IMicroserviceTenantEngine<DeviceManagementTenantConfiguration> {

    /**
     * Get associated device management implementation.
     * 
     * @return
     */
    public IDeviceManagement getDeviceManagement();

    /**
     * Get implementation class that wraps device management with GRPC conversions.
     * 
     * @return
     */
    public DeviceManagementGrpc.DeviceManagementImplBase getDeviceManagementImpl();

    /**
     * Get Kafka producer for events generated by device interactions.
     * 
     * @return
     */
    public IDeviceInteractionEventsProducer getDeviceInteractionEventsProducer();

    /**
     * Get provider which provides an RDB entity manager for this tenant.
     * 
     * @return
     */
    public IRdbEntityManagerProvider getRdbEntityManagerProvider();
}