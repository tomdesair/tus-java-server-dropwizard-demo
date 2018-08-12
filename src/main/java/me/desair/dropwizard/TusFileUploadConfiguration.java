package me.desair.dropwizard;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.client.HttpClientConfiguration;
import org.hibernate.validator.constraints.*;

public class TusFileUploadConfiguration extends Configuration {

    @Valid
    @NotNull
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();

    @NotEmpty
    private String tusDataPath = System.getProperty("java.io.tmpdir");

    private Long uploadExpirationInMilliseconds = 1000L * 60 * 60 * 24;

    @JsonProperty("httpClient")
    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClient;
    }

    @JsonProperty("httpClient")
    public void setHttpClientConfiguration(HttpClientConfiguration httpClient) {
        this.httpClient = httpClient;
    }

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
