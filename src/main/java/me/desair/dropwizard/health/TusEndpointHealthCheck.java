package me.desair.dropwizard.health;

import com.codahale.metrics.health.HealthCheck;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpOptions;

public class TusEndpointHealthCheck extends HealthCheck {

    private String tusUploadUrl;
    private HttpClient httpClient;

    public TusEndpointHealthCheck(String tusUploadUrl, HttpClient httpClient){
        this.tusUploadUrl = tusUploadUrl;
        this.httpClient = httpClient;
    }

    @Override
    protected Result check() throws Exception {
        HttpOptions httpOptions = new HttpOptions(tusUploadUrl);

        HttpResponse httpResponse = httpClient.execute(httpOptions);
        Header tusResumableHeader = httpResponse.getFirstHeader("Tus-Resumable");
        if (tusResumableHeader != null && StringUtils.equals(tusResumableHeader.getValue(), "1.0.0")) {
            Header tusExtensionHeader = httpResponse.getFirstHeader("Tus-Extension");
            if (tusExtensionHeader != null && StringUtils.contains(tusExtensionHeader.getValue(), "creation")) {
                return Result.healthy();
            } else {
                return Result.unhealthy(tusUploadUrl + " returned invalid Tus-Extension header");
            }
        } else {
            return Result.unhealthy(tusUploadUrl + " returned invalid Tus-Resumable header");
        }
    }
}
