package com.vzsme.controllerService;

import com.vzsme.controllerService.model.AppProperties.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Configuration
@ConfigurationProperties(prefix = "controller"/* DON'T CHANGE */, ignoreInvalidFields = true )
public class AppConfigProperties {

    public Props props = new Props(); // Optional Configuration Property


    // NOTE: the setter must match the property name in the application.properties file
    public void setProps(Props props) { this.props = props; }
}
