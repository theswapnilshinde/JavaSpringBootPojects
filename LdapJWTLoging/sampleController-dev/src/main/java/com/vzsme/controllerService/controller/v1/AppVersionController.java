package com.vzsme.controllerService.controller.v1;

import com.vzsme.controllerService.AppConfigProperties;
import com.vzsme.controllerService.controller.v1.response.AppVersionResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(UriConstantsV1.version)
public class AppVersionController extends BaseController {

    @Autowired
    protected AppConfigProperties appConfig;

    @ApiOperation(value = "Get the version information for this service") //, tags = "Admin")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public AppVersionResponse getVersion(HttpServletRequest request)
    {
        String vendor = appConfig.props.getVendor();
        String version = appConfig.props.getVersion();
        String name = appConfig.props.getName();
        String desc = appConfig.props.getDescription();

        return new AppVersionResponse(version,
                String.format("%s %s", vendor, name).trim(), // Name
                String.format("%s %s", vendor, desc).trim()  // Description
        );
    }
}
