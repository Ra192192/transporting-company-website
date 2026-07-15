package com.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leads")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 24)
    private String phone;

    @Column(name = "what_to_remove", length = 600)
    private String whatToRemove;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private LeadStatus status = LeadStatus.UNPROCESSED;

    protected Lead() {
    }

    public Lead(String name, String phone, String whatToRemove) {
        this.name = name;
        this.phone = phone;
        this.whatToRemove = whatToRemove;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getWhatToRemove() {
        return whatToRemove;
    }

    public LeadStatus getStatus() {
        return status;
    }
}
