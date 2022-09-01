package com.day13.apachePoiDemo.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="cryptocurrency")
@AllArgsConstructor
@NoArgsConstructor
public class Cryptocurrency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String ticker;

    @Column(name = "coin_id")
    private Long coinId;
    private String code;
    private String exchange;
    private short invalid;

    @Column(name = "record_time")
    private Integer recordTime;
    private Long usd;
    private Long idr;
    private Long hnst;
    private Long eth;
    private Long btc;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
