package com.sample.web.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.web.dto.ClientDataDTO;
import com.sample.web.dto.ClientIm1InfoDTO;
import com.sample.web.dto.ClientIm2InfoDTO;
import com.sample.web.dto.HealthInfoDTO;

import java.io.IOException;

public class DataUtils {
    public static ClientIm1InfoDTO transformDataToIm1DTO(ClientDataDTO clientDataDTO) {
        String locationInfo = clientDataDTO.getLocation();
        String[] locationInfoList = locationInfo.split("-");
        String streetLocation = locationInfoList[0];
        String cityLocation = locationInfoList[1];
        return new ClientIm1InfoDTO(
                clientDataDTO.getId(),
                clientDataDTO.getName(),
                streetLocation,
                cityLocation,
                clientDataDTO.getEmail(),
                clientDataDTO.getJsonHealthData()
        );
    }

    public static ClientIm2InfoDTO transformDataToIm2DTO(ClientIm1InfoDTO clientIm1InfoDTO) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        String jsonHealthInfo = clientIm1InfoDTO.getJsonHealthData();
        HealthInfoDTO healthInfoDTO = mapper.readValue(jsonHealthInfo, HealthInfoDTO.class);
        return new ClientIm2InfoDTO(
                clientIm1InfoDTO.getId(),
                clientIm1InfoDTO.getName(),
                clientIm1InfoDTO.getStreetLocation(),
                clientIm1InfoDTO.getCityLocation(),
                clientIm1InfoDTO.getEmail(),
                healthInfoDTO
        );
    }

    public static String convertObjectToJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
