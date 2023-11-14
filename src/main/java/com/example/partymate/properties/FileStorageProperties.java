package com.example.partymate.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Unagi_zoso
 * @since 2023-11-12
 */
@Getter
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    private String uploadDir;

    public void setUploadDir(String uploadDir) {
            this.uploadDir = uploadDir;
        }
}
