package com.sample.web.service;

import com.sample.web.dto.ClientDataDTO;
import org.springframework.stereotype.Service;

@Service
public class DataSimulateService {
    public ClientDataDTO findDataById(Long id) {
        if (id == 3L) {//good
            return new ClientDataDTO(3L, "customer3", "yenhoa-hanoi",
                    "customer3@email.com", "{'height':175, 'weight':80}");
        } else if (id == 2L) { // bad
            return new ClientDataDTO(2L, "customer2", "lang-hanoi",
                    "customer2@email.com", "{'height':120, 'weight':60}");
        } else if (id == 4L) { //normal
            return new ClientDataDTO(4L, "customer4", "minhkhai-hanoi",
                    "customer4@email.com", "{'height':140, 'weight':75}");
        }
        else {
            return null;
        }

    }
}
