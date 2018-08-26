package com.sample.web.service;

import com.sample.web.dto.ClientHealthStatusInfo;
import com.sample.web.dto.ClientIm2InfoDTO;
import com.sample.web.dto.HealthInfoDTO;
import org.springframework.stereotype.Service;

@Service
public class HttpClientSimulateService {
    public ClientHealthStatusInfo getClientHealthStatusInfo(ClientIm2InfoDTO clientInputInfo){
        HealthInfoDTO healthInfo = clientInputInfo.getHealthDataInfo();
        Long height = healthInfo.getHeight();
        Long weight = healthInfo.getWeight();
        String healthStatus;
        if(height>150 && weight > 70){
            healthStatus = "good";
        }else if(height>130 && weight > 50){
            healthStatus = "normal";
        }else{
            healthStatus = "bad";
        }
        return new ClientHealthStatusInfo(
          clientInputInfo.getId(),
          clientInputInfo.getName(),
          clientInputInfo.getStreetLocation(),
          clientInputInfo.getCityLocation(),
          clientInputInfo.getEmail(),
          healthStatus
        );
    }
}
