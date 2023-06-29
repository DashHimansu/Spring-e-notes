package com.ENotes.ENotesTaker.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit {

    @Temporal(TemporalType.DATE)
    @CreatedDate
    @Column(name="create_date",nullable = false, updatable = false)
    private Date createDate;
    @Temporal(TemporalType.DATE)
    @CreatedDate
    @Column(name="update_date",nullable = false)
    private Date updateDate;
}
