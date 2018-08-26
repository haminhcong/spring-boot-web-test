package com.sample.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientIm2InfoDTO {
    private Long id;
    private String name;
    private String streetLocation;
    private String cityLocation;
    private String email;
    private HealthInfoDTO healthDataInfo;
}