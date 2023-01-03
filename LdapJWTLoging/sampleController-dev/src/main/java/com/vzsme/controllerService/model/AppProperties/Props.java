package com.vzsme.controllerService.model.AppProperties;

import org.springframework.stereotype.Component;

@Component
public class Props {
    //
    // Static generic properties
    //
    private String version = "0.0.0";
    private String name = "Sample Controller";
    private String description = "Sample stack controller service for Netprizm";

    //
    // Overridable properties from file: application.properties
    //
    private String vendor = "";
    private int maxThreads = 2;


    // NOTE: These do NOT have setter methods because they should not be overridden.
    // This is because these properties are used by Swagger and the application.properties
    // are not loaded when
    public String getVersion() { return this.version; }
    public String getName() { return this.name; }
    public String getDescription() { return this.description; }


    //
    // Application.properties: controller.props.XXXX
    // NOTE: The setter must match the property name so the framework can auto-load
    //
    public String getVendor() { return this.vendor; }
    public void setVendor(String value) { this.vendor = value; }


    public int getMaxThreads() { return this.maxThreads; }
    public void setMaxThreads(int value) { this.maxThreads = value; }
}
