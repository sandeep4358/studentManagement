package com.smanagement.entity.auditor;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Auditable {
    @Column(name = "create_by", updatable = false)
    private String createBy;
    @Column(name = "create_date", updatable = false, nullable = false)
    private Instant createDate;
    //Using instant to store date and time in UTC consistency. If you use LocalDateTime, it may create issues in different time zones.
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;


}
