package me.desair.dropwizard;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

public class TusFileUploadConfiguration extends Configuration {

    @NotEmpty
    private String tusDataPath = System.getProperty("java.io.tmpdir");

    @JsonProperty
    public String getTusDataPath() {
        return tusDataPath;
    }

    @JsonProperty
    public void setTusDataPath(String name) {
        this.tusDataPath = name;
    }
}
