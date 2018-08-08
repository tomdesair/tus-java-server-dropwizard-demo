package me.desair.dropwizard;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

public class TusFileUploadConfiguration extends Configuration {

    @NotEmpty
    private String tusDataPath = System.getProperty("java.io.tmpdir");

    private Long uploadExpirationInMilliseconds = 1000L * 60 * 60 * 24;

    @JsonProperty
    public String getTusDataPath() {
        return tusDataPath;
    }

    @JsonProperty
    public void setTusDataPath(String name) {
        this.tusDataPath = name;
    }

    @JsonProperty
    public void setUploadExpirationInMilliseconds(Long uploadExpirationInMilliseconds) {
        this.uploadExpirationInMilliseconds = uploadExpirationInMilliseconds;
    }

    @JsonProperty
    public Long getUploadExpirationInMilliseconds() {
        return uploadExpirationInMilliseconds;
    }
}
