package com.friend.cleanup;

import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContactController {
    private final LeadRepository leadRepository;

    public ContactController(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @PostMapping("/contact")
    public ResponseEntity<Map<String, String>> createContact(@Valid @RequestBody ContactRequest request) {
        Lead lead = leadRepository.save(new Lead(
                request.name().trim(),
                request.phone().trim(),
                request.message() == null ? "" : request.message().trim()
        ));

        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "id", lead.getId().toString(),
                "message", "Заявка принята. Скоро вам перезвонят."
        ));
    }
}
