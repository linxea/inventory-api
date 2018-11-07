package herman.test.controller;

import herman.test.model.StatusResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
public class StatusController {
    @GetMapping(path="/status")
    public StatusResponse status() {
        StatusResponse response = new StatusResponse();
        response.setStatus("OK");
        response.setServerTime(new Date());
        return response;
    }
}
