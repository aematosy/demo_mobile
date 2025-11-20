package com.store.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class YouTubePermissionPage {

    // Botón "Allow" del popup de permisos - LOCALIZADOR CORRECTO
    public static final Target ALLOW_BUTTON = Target.the("permission allow button")
            .located(By.id("com.android.permissioncontroller:id/permission_allow_button"));
}
