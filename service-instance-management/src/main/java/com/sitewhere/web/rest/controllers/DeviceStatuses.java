/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.web.rest.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirements;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.sitewhere.instance.spi.microservice.IInstanceManagementMicroservice;
import com.sitewhere.microservice.api.device.IDeviceManagement;
import com.sitewhere.rest.model.search.device.DeviceStatusSearchCriteria;
import com.sitewhere.spi.SiteWhereException;

import io.swagger.annotations.Api;

/**
 * Controller for device status operations.
 */
@Path("/api/statuses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "statuses")
@Tag(name = "Device Statuses", description = "Services for querying device statuses independently of device type.")
@SecurityRequirements({ @SecurityRequirement(name = "jwtAuth", scopes = {}),
	@SecurityRequirement(name = "tenantIdHeader", scopes = {}),
	@SecurityRequirement(name = "tenantAuthHeader", scopes = {}) })
public class DeviceStatuses {

    @Inject
    private IInstanceManagementMicroservice<?> microservice;

    /**
     * List statuses that match the given criteria.
     * 
     * @param deviceTypeToken
     * @param code
     * @param page
     * @param pageSize
     * @return
     * @throws SiteWhereException
     */
    @GET
    @Operation(summary = "List device statuses that match criteria.", description = "List device statuses that match criteria.")
    public Response listDeviceStatuses(
	    @Parameter(description = "Device type token", required = false) @QueryParam("deviceTypeToken") String deviceTypeToken,
	    @Parameter(description = "Status code", required = false) @QueryParam("code") String code,
	    @Parameter(description = "Page number", required = false) @QueryParam("page") @DefaultValue("1") int page,
	    @Parameter(description = "Page size", required = false) @QueryParam("pageSize") @DefaultValue("100") int pageSize)
	    throws SiteWhereException {
	DeviceStatusSearchCriteria criteria = new DeviceStatusSearchCriteria(page, pageSize);
	criteria.setDeviceTypeToken(deviceTypeToken);

	// Add code if specified.
	if (code != null) {
	    criteria.setCode(code);
	}

	return Response.ok(getDeviceManagement().listDeviceStatuses(criteria)).build();
    }

    protected IDeviceManagement getDeviceManagement() {
	return getMicroservice().getDeviceManagement();
    }

    protected IInstanceManagementMicroservice<?> getMicroservice() {
	return microservice;
    }
}
