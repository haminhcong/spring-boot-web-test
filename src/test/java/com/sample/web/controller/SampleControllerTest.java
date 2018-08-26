package com.sample.web.controller;

import com.sample.web.dto.ClientDataDTO;
import com.sample.web.dto.ClientHealthStatusInfo;
import com.sample.web.dto.ClientIm2InfoDTO;
import com.sample.web.dto.HealthInfoDTO;
import com.sample.web.service.DataSimulateService;
import com.sample.web.service.HttpClientSimulateService;
import com.sample.web.utils.DataUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {

    @MockBean
    private DataSimulateService dataSimulateService;
    @MockBean
    private HttpClientSimulateService httpClientSimulateService;

    private Long clientId = 3L;
    private ClientDataDTO clientWithId3Fake = new ClientDataDTO(
            3L,
            "customer3",
            "yenhoa-hanoi",
            "customer3@email.com",
            "{'height':175, 'weight':80}");

    // correct equals Object
    private ClientIm2InfoDTO im2InfoDTOFakeCorrect = new ClientIm2InfoDTO(
            3L,
            "customer3",
            "yenhoa",
            "hanoi",
            "customer3@email.com",
            new HealthInfoDTO(175L, 80L)
    );
    // incorrect  not equals Object
    private ClientIm2InfoDTO im2InfoDTOFakeInCorrect = new ClientIm2InfoDTO(
            4L,
            "customer4",
            "yenhoa1234",
            "hanoi",
            "customer3@email.com",
            new HealthInfoDTO(175L, 80L)
    );

    private ClientHealthStatusInfo healthStatusInfoFakeOutput =
            new ClientHealthStatusInfo(
                    3L,
                    "customer3",
                    "yenhoa",
                    "hanoi",
                    "customer3@email.com",
                    "good"
            );

    @Autowired
    private MockMvc mockMvc;

    // Test Passed when we input correct Object
    @Test
    public void getGoodClientStatusInfoPassTest() throws Exception {
        when(dataSimulateService
                .findDataById(clientId))
                .thenReturn(clientWithId3Fake);

        when(httpClientSimulateService
                .getClientHealthStatusInfo(im2InfoDTOFakeCorrect))
                .thenReturn(healthStatusInfoFakeOutput);
        this.mockMvc
                .perform(get("/data/client")
                        .param("id", clientId.toString())
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                ).andExpect(status().isOk())
                .andExpect(content().json(
                        DataUtils.convertObjectToJsonString(healthStatusInfoFakeOutput)));
    }

    // Test Failed when we input incorrect Object
    @Test
    public void getGoodClientStatusInfoFailedTest() throws Exception {
        when(dataSimulateService
                .findDataById(clientId))
                .thenReturn(clientWithId3Fake);

        when(httpClientSimulateService
                .getClientHealthStatusInfo(im2InfoDTOFakeInCorrect))
                .thenReturn(healthStatusInfoFakeOutput);
        this.mockMvc
                .perform(get("/data/client")
                        .param("id", clientId.toString())
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                ).andExpect(status().isOk())
                .andExpect(content().json(
                        DataUtils.convertObjectToJsonString(healthStatusInfoFakeOutput)));
    }
}

