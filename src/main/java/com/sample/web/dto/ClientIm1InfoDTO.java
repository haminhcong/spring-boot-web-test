package com.sample.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientIm1InfoDTO {
    private Long id;
    private String name;
    private String streetLocation;
    private String cityLocation;
    private String email;
    private String jsonHealthData;
}
