package com.day13.apachePoiDemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="cryptocurrency")
public class Cryptocurrency implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "coin_id")
    private Long coinId;

    @Column(name = "code")
    private String code;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "invalid")
    private short invalid;

    @Column(name = "record_time")
    private Integer recordTime;

    @Column(name = "usd")
    private Long usd;

    @Column(name = "idr")
    private Long idr;

    @Column(name = "hnst")
    private Long hnst;

    @Column(name = "eth")
    private Long eth;

    @Column(name = "btc")
    private Long btc;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
