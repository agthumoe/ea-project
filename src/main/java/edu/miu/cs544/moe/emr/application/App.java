package edu.miu.cs544.moe.emr.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;

@ConfigurationProperties("application")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class App implements Serializable {
    @Serial
    private static final long serialVersionUID = -4458820757458721437L;
    private String title;
    private String version;
    private String description;
    private AppInfo info;
}
