package com.sample.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDataDTO {
    private Long id;
    private String name;
    private String location;
    private String email;
    private String jsonHealthData;
}
