package com.example.partymate.domain.util;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-12
 */
public class EntityListener {
    @PrePersist
    public void setCreateTime(Object o) {
        if (o instanceof Auditable) {
            ((Auditable) o).setCreatedDateTime(LocalDateTime.now());
            ((Auditable) o).setUpdatedDateTime(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void setUpdateTime(Object o) {
        if (o instanceof Auditable) {
            ((Auditable) o).setUpdatedDateTime(LocalDateTime.now());
        }
    }
}
