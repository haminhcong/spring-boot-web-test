package com.sample.web.controller;

import com.sample.web.dto.ClientDataDTO;
import com.sample.web.dto.ClientHealthStatusInfo;
import com.sample.web.dto.ClientIm1InfoDTO;
import com.sample.web.dto.ClientIm2InfoDTO;
import com.sample.web.service.DataSimulateService;
import com.sample.web.service.HttpClientSimulateService;
import com.sample.web.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/data")
public class SampleController {

    private DataSimulateService dataSimulateService;
    private HttpClientSimulateService httpClientSimulateService;

    @Autowired
    public SampleController(
            DataSimulateService dataSimulateService,
            HttpClientSimulateService httpClientSimulateService
    ) {
        this.dataSimulateService = dataSimulateService;
        this.httpClientSimulateService = httpClientSimulateService;
    }

    @RequestMapping(path = "/client", method = RequestMethod.GET)
    public ClientHealthStatusInfo getClientInfo(@RequestParam Long id) throws IOException {
        ClientDataDTO clientDbData = dataSimulateService.findDataById(id);
        ClientIm1InfoDTO clientIm1InfoDTO = DataUtils.transformDataToIm1DTO(clientDbData);
        ClientIm2InfoDTO clientIm2InfoDTO = DataUtils.transformDataToIm2DTO(clientIm1InfoDTO);
        ClientHealthStatusInfo clientHealthStatusInfo =
                httpClientSimulateService.getClientHealthStatusInfo(clientIm2InfoDTO);
        log.info(DataUtils.convertObjectToJsonString(clientHealthStatusInfo));
        return clientHealthStatusInfo;
    }

}
