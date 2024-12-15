package edu.miu.cs544.moe.emr.application;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class AppInfoEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] parts = text.split(",");
        AppInfo appInfo = new AppInfo(parts[0], parts[1], parts[2]);
        setValue(appInfo);
    }
}
