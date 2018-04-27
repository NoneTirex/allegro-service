package pl.edu.tirex.allegro.service.system.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController
{
    @GetMapping("/api/status")
    public String healthCheck()
    {
        return "ok";
    }
}
