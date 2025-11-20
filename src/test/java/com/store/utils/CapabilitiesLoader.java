package com.store.utils;

import org.json.JSONObject;
import org.openqa.selenium.MutableCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class CapabilitiesLoader {

    private static final String CAPABILITIES_BASE_PATH = "/capabilities/";

    /**
     * Carga capabilities desde un archivo JSON
     * @param platform "android", "ios", "android-tablet", etc.
     * @return MutableCapabilities configuradas
     */
    public static MutableCapabilities loadCapabilities(String platform) {
        // OBTENER ENVIRONMENT Y DEVICE TYPE
        String environment = System.getProperty("environment", "android").toLowerCase();
        String deviceType = System.getProperty("device.type", "emulator").toLowerCase();

        System.out.println("\n===== CAPABILITIES LOADER =====");
        System.out.println("   Platform recibida: " + platform);
        System.out.println("   Environment: " + environment);
        System.out.println("   Device Type: " + deviceType);

        // CONSTRUIR EL NOMBRE DEL ARCHIVO
        // Formato: {environment}-{deviceType}-capabilities.json
        // Ejemplo: android-real-capabilities.json, ios-emulator-capabilities.json
        String fileName = environment + "-" + deviceType + "-capabilities.json";

        // Permitir override via system property
        String customFile = System.getProperty("capabilities." + environment + "." + deviceType + ".file");
        if (customFile != null && !customFile.isEmpty()) {
            fileName = customFile;
            System.out.println("   Usando archivo personalizado: " + fileName);
        }

        String resourcePath = CAPABILITIES_BASE_PATH + fileName;

        try {
            System.out.println("   Cargando desde: " + resourcePath);

            // Cargar desde resources usando ClassLoader
            InputStream inputStream = CapabilitiesLoader.class.getResourceAsStream(resourcePath);

            if (inputStream == null) {
                throw new RuntimeException("Archivo de capabilities no encontrado: " + resourcePath +
                        "\n   Verifica que existe el archivo en src/test/resources/capabilities/");
            }

            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            JSONObject json = new JSONObject(content);

            MutableCapabilities capabilities = new MutableCapabilities();

            // Cargar capabilities desde JSON
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = json.get(key);
                addCapability(capabilities, key, value);
            }

            // Agregar capabilities adicionales desde system properties (si existen)
            addAdditionalCapabilities(capabilities, environment);

            System.out.println("   Capabilities cargadas: " + capabilities.asMap().size() + " propiedades");
            System.out.println("===================================\n");

            return capabilities;

        } catch (IOException e) {
            throw new RuntimeException("Error al leer archivo de capabilities: " + resourcePath, e);
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar capabilities: " + e.getMessage(), e);
        }
    }

    /**
     * Agrega capabilities adicionales desde system properties
     * Ejemplo: -Dappium.deviceName=Pixel8 -Dappium.platformVersion=14
     */
    private static void addAdditionalCapabilities(MutableCapabilities capabilities, String platform) {
        System.getProperties().forEach((key, value) -> {
            String keyStr = key.toString();

            // Buscar propiedades que empiecen con "appium."
            if (keyStr.startsWith("appium.")) {
                String capabilityKey = keyStr.substring(7); // Remover "appium."
                System.out.println("   Sobrescribiendo capability: appium:" + capabilityKey + " = " + value);

                Object convertedValue = convertValue(value.toString());
                capabilities.setCapability("appium:" + capabilityKey, convertedValue);
            }
        });
    }

    /**
     * Agrega una capability al objeto MutableCapabilities
     * CORREGIDO: No duplicar el prefijo appium:
     */
    private static void addCapability(MutableCapabilities capabilities, String key, Object value) {
        // Si la key ya tiene "appium:" NO lo agregues de nuevo
        if (key.startsWith("appium:")) {
            capabilities.setCapability(key, value);
            return;
        }

        // Si es platformName o automationName, van SIN prefijo appium:
        if (key.equals("platformName") || key.equals("automationName")) {
            capabilities.setCapability(key, value);
            return;
        }

        // Para el resto, agregar prefijo appium:
        capabilities.setCapability("appium:" + key, value);
    }

    /**
     * Convierte un String a su tipo apropiado
     */
    private static Object convertValue(String value) {
        if (value == null) return value;

        // Boolean
        if (value.equalsIgnoreCase("true")) return true;
        if (value.equalsIgnoreCase("false")) return false;

        // Integer
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // No es un numero, devolver como String
        }

        return value;
    }

    /**
     * Imprime las capabilities cargadas para debug
     */
    public static void printCapabilities(MutableCapabilities capabilities) {
        System.out.println("\nCapabilities finales:");
        capabilities.asMap().forEach((key, value) ->
                System.out.println("   • " + key + " = " + value + " (" + value.getClass().getSimpleName() + ")")
        );
        System.out.println();
    }
}
