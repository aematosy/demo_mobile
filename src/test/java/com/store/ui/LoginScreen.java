package com.store.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import com.store.utils.ConfigHelper;

public class LoginScreen {

    // ----------- ANDROID -----------
    public static final Target MENU_BUTTON_ANDROID = Target.the("botón de menú (Android)")
            .located(By.xpath("//android.widget.ImageView[@content-desc='View menu']"));

    public static final Target LOGIN_OPTION_MENU_ANDROID = Target.the("opción login en menú (Android)")
            .located(By.xpath("//android.widget.TextView[@content-desc='Login Menu Item']"));

    public static final Target LOGIN_SCREEN_TITLE_ANDROID = Target.the("titulo de pantalla de login (Android)")
            .located(By.xpath("//android.widget.TextView[@resource-id='com.saucelabs.mydemoapp.android:id/loginTV']"));

    public static final Target USERNAME_FIELD_ANDROID = Target.the("campo de usuario (Android)")
            .located(By.xpath("//android.widget.EditText[@resource-id='com.saucelabs.mydemoapp.android:id/nameET']"));

    public static final Target PASSWORD_FIELD_ANDROID = Target.the("campo de contraseña (Android)")
            .located(By.xpath("//android.widget.EditText[@resource-id='com.saucelabs.mydemoapp.android:id/passwordET']"));

    public static final Target LOGIN_BUTTON_ANDROID = Target.the("botón de login (Android)")
            .located(By.xpath("//android.widget.Button[@content-desc='Tap to login with given credentials']"));

    public static final Target LOGOUT_OPTION_MENU_ANDROID = Target.the("opción logout en menú (Android)")
            .located(By.xpath("//android.widget.TextView[@content-desc='Logout Menu Item']"));

    // ----------- iOS -----------
    public static final Target MENU_BUTTON_IOS = Target.the("botón de menú (iOS)")
            .located(By.xpath("//XCUIElementTypeImage[@name='Menu Icons']"));

    public static final Target LOGIN_OPTION_MENU_IOS = Target.the("opción login en menú (iOS)")
            .located(By.xpath("//XCUIElementTypeButton[@name='LogOut-menu-item']"));

    public static final Target LOGIN_SCREEN_TITLE_IOS = Target.the("titulo de pantalla de login (iOS)")
            .located(By.xpath("(//XCUIElementTypeStaticText[@name='Login'])[1]"));

    public static final Target USERNAME_FIELD_IOS = Target.the("campo de usuario (iOS)")
            .located(By.xpath("//XCUIElementTypeTextField"));

    public static final Target PASSWORD_FIELD_IOS = Target.the("campo de contraseña (iOS)")
            .located(By.xpath("//XCUIElementTypeSecureTextField"));

    public static final Target LOGIN_BUTTON_IOS = Target.the("botón de login (iOS)")
            .located(By.xpath("//XCUIElementTypeButton[@name='Login']"));

    public static final Target LOGOUT_OPTION_MENU_IOS = Target.the("opción logout en menú (iOS)")
            .located(By.xpath("//XCUIElementTypeButton[@name='LogOut-menu-item']"));

    // ----------- MÉTODOS DINÁMICOS -----------

    public static Target menuButton() {
        return ConfigHelper.isAndroid() ? MENU_BUTTON_ANDROID : MENU_BUTTON_IOS;
    }

    public static Target loginOptionMenu() {
        return ConfigHelper.isAndroid() ? LOGIN_OPTION_MENU_ANDROID : LOGIN_OPTION_MENU_IOS;
    }

    public static Target loginScreenTitle() {
        return ConfigHelper.isAndroid() ? LOGIN_SCREEN_TITLE_ANDROID : LOGIN_SCREEN_TITLE_IOS;
    }

    public static Target usernameField() {
        return ConfigHelper.isAndroid() ? USERNAME_FIELD_ANDROID : USERNAME_FIELD_IOS;
    }

    public static Target passwordField() {
        return ConfigHelper.isAndroid() ? PASSWORD_FIELD_ANDROID : PASSWORD_FIELD_IOS;
    }

    public static Target loginButton() {
        return ConfigHelper.isAndroid() ? LOGIN_BUTTON_ANDROID : LOGIN_BUTTON_IOS;
    }

    public static Target logoutOption() {
        return ConfigHelper.isAndroid() ? LOGOUT_OPTION_MENU_ANDROID : LOGOUT_OPTION_MENU_IOS;
    }
}