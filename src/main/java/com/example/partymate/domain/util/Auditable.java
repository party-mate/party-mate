package com.example.partymate.domain.util;

import java.time.LocalDateTime;

/**
 * @author : Unagi_zoso
 * @date : 2023-10-12
 */
public interface Auditable {

    LocalDateTime getCreatedDateTime();
    LocalDateTime getUpdatedDateTime();

    void setCreatedDateTime(LocalDateTime createdDateTime);
    void setUpdatedDateTime(LocalDateTime updatedDateTime);
}
