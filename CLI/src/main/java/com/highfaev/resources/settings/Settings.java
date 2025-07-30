package com.highfaev.resources.settings;

import lombok.Data;

@Data
public class Settings {
    private String databaseUrl;
    private String username;
    private String password;
    private boolean offlineMode;
}
