package com.task.infra.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "wikipedia")
@Component
@Validated
@Data
public class WikiProperties {

    @NotNull
    private String url;

}
