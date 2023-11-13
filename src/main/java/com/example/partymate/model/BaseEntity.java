package com.example.partymate.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-13
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    private Integer erasedFlag = 0; // 0: false, 1: true, 삭제 요청 시 이 값을 수정한다.

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdDateTime;

    @LastModifiedDate
    LocalDateTime updatedDateTime;
}
