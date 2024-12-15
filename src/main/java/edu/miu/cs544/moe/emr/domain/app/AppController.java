package edu.miu.cs544.moe.emr.domain.app;

import edu.miu.cs544.moe.emr.application.App;
import edu.miu.cs544.moe.emr.application.AppInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @Value("${application.info}")
    private AppInfo appInfo;

    @Autowired
    private App app;

    @GetMapping("/api/v1/app-info")
    public AppInfo getAppInfo() {
        return appInfo;
    }

    @GetMapping("/api/v1/app")
    public ResponseEntity<App> getApp() {
        System.out.println(app);
        return ResponseEntity.ok(app);
    }
}
