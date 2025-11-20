package com.store.ui;

import com.store.utils.ConfigHelper;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductsScreen {

    public static final Target PRODUCTS_TITLE_ANDROID =
            Target.the("products title")
                    .located(By.xpath("//android.widget.TextView[@content-desc='title']"));

    public static final Target FIRST_PRODUCT =
            Target.the("first product")
                    .located(By.xpath("(//android.view.ViewGroup[@content-desc='productItem'])[1]"));

    public static final Target PRODUCT_NAME =
            Target.the("product name")
                    .located(By.xpath("//android.widget.TextView[@content-desc='productName']"));

    public static final Target PRODUCT_PRICE =
            Target.the("product price")
                    .located(By.xpath("//android.widget.TextView[@content-desc='productPrice']"));

    public static final Target ADD_TO_CART_BUTTON =
            Target.the("add to cart button")
                    .located(By.xpath("//android.view.ViewGroup[@content-desc='addToCartBtn']"));

    public static final Target PRODUCTS_TITLE_IOS =
            Target.the("products title")
                    .located(By.xpath("//XCUIElementTypeStaticText[@name='title']"));

    // ----------- MÉTODOS DINÁMICOS -----------
    public static Target title_product() {
        return ConfigHelper.isAndroid() ? PRODUCTS_TITLE_ANDROID : PRODUCTS_TITLE_IOS;
    }
}
