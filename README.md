# Mobile Testing Framework - Serenity BDD + Appium

Framework de automatización de pruebas móviles (Android/iOS) utilizando Serenity BDD 4.0.12, Appium 8.3.0 y Screenplay Pattern.

---

## Tabla de Contenidos

- [Requisitos Previos](#-requisitos-previos)
- [Instalación del Entorno](#-instalación-del-entorno)
- [Configuración del Proyecto](#-configuración-del-proyecto)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Configuración de Capabilities](#-configuración-de-capabilities)
- [Ejecución de Pruebas](#-ejecución-de-pruebas)
- [Reportes](#-reportes)
- [Comandos 脷tiles](#-comandos-útiles)
- [Troubleshooting](#-troubleshooting)
- [Recursos Adicionales](#-recursos-adicionales)

---

## Requisitos Previos

### Versiones del Framework

| Tecnología | Versión |
|------------|---------|
| Java | 17 |
| Maven | 3.8+ |
| Node.js | 18+ LTS |
| Appium | 2.11.5 |
| Serenity BDD | 4.0.12 |
| Appium Java Client | 8.3.0 |
| Selenium | 4.12.1 |

---

## Instalación del Entorno

### 1. Node.js

```bash
# Descarga Node.js desde https://nodejs.org/
# Instala la versión LTS (18.x o superior)

# Verificar instalación
node --version
npm --version
```

**Configuración de Variables de Entorno:**

**Windows:**
```
Variable: NODE_HOME
Valor: C:\Program Files\nodejs

Agregar a PATH:
%NODE_HOME%
```

**macOS/Linux:**
```bash
# Agregar a ~/.bash_profile o ~/.zshrc
export PATH="/usr/local/bin:$PATH"
```

---

### 2. Java JDK 17

**Descargar:**
- Oracle JDK: https://www.oracle.com/java/technologies/downloads/#java17
- OpenJDK: https://adoptium.net/

**Configuración de Variables de Entorno:**

**Windows:**
```
Variable: JAVA_HOME
Valor: C:\Program Files\Java\jdk-17

Agregar a PATH:
%JAVA_HOME%\bin
```

**macOS/Linux:**
```bash
# Agregar a ~/.bash_profile o ~/.zshrc
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
```

**Verificar:**
```bash
java -version
javac -version
```

---

### 3. Maven

**Descargar:**
- https://maven.apache.org/download.cgi

**Configuración de Variables de Entorno:**

**Windows:**
```
Variable: MAVEN_HOME
Valor: C:\Program Files\Apache\maven

Agregar a PATH:
%MAVEN_HOME%\bin
```

**macOS/Linux:**
```bash
# Agregar a ~/.bash_profile o ~/.zshrc
export MAVEN_HOME=/usr/local/apache-maven-3.9.5
export PATH=$MAVEN_HOME/bin:$PATH
```

**Verificar:**
```bash
mvn -version
```

---

### 4. Android SDK

**Opción 1: Android Studio (Recomendado)**
- Descarga: https://developer.android.com/studio
- Durante instalación, marca: Android SDK, Android SDK Platform, Android Virtual Device

**Opción 2: Command Line Tools**
- Descarga: https://developer.android.com/studio#command-tools

**Configuración de Variables de Entorno:**

**Windows:**
```
Variable: ANDROID_HOME
Valor: C:\Users\TuUsuario\AppData\Local\Android\Sdk

Agregar a PATH:
%ANDROID_HOME%\platform-tools
%ANDROID_HOME%\tools
%ANDROID_HOME%\tools\bin
%ANDROID_HOME%\emulator
```

**macOS/Linux:**
```bash
# Agregar a ~/.bash_profile o ~/.zshrc
export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$ANDROID_HOME/platform-tools:$PATH
export PATH=$ANDROID_HOME/tools:$PATH
export PATH=$ANDROID_HOME/tools/bin:$PATH
export PATH=$ANDROID_HOME/emulator:$PATH
```

**Verificar:**
```bash
adb version
emulator -version
```

**Instalar paquetes necesarios:**
```bash
# Platform Tools
sdkmanager "platform-tools"

# Android 14 (API 34)
sdkmanager "platforms;android-34"
sdkmanager "build-tools;34.0.0"

# System Image para emulador
sdkmanager "system-images;android-34;google_apis;x86_64"

# Verificar instalación
sdkmanager --list
```

---

### 5. Appium

**Instalación Global:**
```bash
# Desinstalar versiones anteriores (si existen)
npm uninstall -g appium

# Instalar Appium 2.x
npm install -g appium@2.11.5

# Verificar versión
appium --version
```

**Instalar Drivers:**
```bash
# Driver para Android
appium driver install uiautomator2@2.34.2

# Driver para iOS (solo en macOS)
appium driver install xcuitest

# Verificar drivers instalados
appium driver list
```

**Salida esperada:**
```
鉁?Listing available drivers
- uiautomator2@2.34.2 [installed (NPM)]
- xcuitest@x.x.x [installed (NPM)]
```

---

### 6. Appium Doctor

**Instalación:**
```bash
npm install -g appium-doctor
```

**Verificar configuración Android:**
```bash
appium-doctor --android
```

**Verificar configuración iOS (macOS):**
```bash
appium-doctor --ios
```

**Salida esperada (Android):**
```
info AppiumDoctor Appium Doctor v.2.1.0
info AppiumDoctor ### Diagnostic for necessary dependencies starting ###
鉁?ANDROID_HOME is set to: /Users/.../Android/sdk
鉁?JAVA_HOME is set to: /Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home
鉁?adb exists at: /Users/.../Android/sdk/platform-tools/adb
鉁?android exists at: /Users/.../Android/sdk/tools/android
鉁?emulator exists at: /Users/.../Android/sdk/emulator/emulator
鉁?Bin directory of $JAVA_HOME is set
info AppiumDoctor ### Diagnostic for necessary dependencies completed, no fix needed. ###
```

---

### 7. Appium ChromeDriver (Configuración)

**Crear archivo `.appiumrc.json` en tu directorio HOME:**

**Windows:** `C:\Users\<TU_USUARIO>\.appiumrc.json`

**macOS/Linux:** `~/.appiumrc.json`

**Contenido:**
```json
{
  "server": {
    "allow-insecure": ["chromedriver_autodownload"]
  }
}
```

Esto permite que Appium descargue automáticamente el ChromeDriver correcto para WebViews y aplicaciones híbridas.

**Verificar configuración:**
```bash
# Windows
type %USERPROFILE%\.appiumrc.json

# macOS/Linux
cat ~/.appiumrc.json
```

---

## Configuración del Proyecto

### 1. Clonar/Descargar el Proyecto

```bash
git clone <tu-repositorio>
cd demo_mobile
```

### 2. Estructura de Carpetas Base

```bash
# Crear carpeta para apps (si no existe)
mkdir -p apps/android
mkdir -p apps/ios

# Copiar tu APK/IPA
cp /path/to/your/app.apk apps/android/demostore.apk
cp /path/to/your/app.ipa apps/ios/demostore.ipa
```

### 3. Instalar Dependencias Maven

```bash
mvn clean install -DskipTests
```

**Esto descargará:**
- Serenity BDD 4.0.12
- Appium Java Client 8.3.0
- Selenium 4.12.1
- Cucumber
- Todas las dependencias necesarias

---

## 馃搨 Estructura del Proyecto

```
demo_mobile/
├─ apps/               # Archivos .apk y .ipa de prueba
│ ├─ android/
│ └─ ios/
├─ src/
│ └─ test/
│ ├─ java/com/store/
│ │ ├─ exceptions/    # Manejo de excepciones personalizadas
│ │ ├─ hooks/         # Hooks para Appium/Serenity (inicio y cierre)
│ │ ├─ models/        # Modelos de datos usados en las pruebas
│ │ ├─ questions/     # Validaciones y consultas tipo Screenplay
│ │ ├─ runners/       # Runners de Serenity BDD (JUnit, Cucumber)
│ │ ├─ steps/         # Definiciones de pasos BDD (Step Definitions)
│ │ ├─ tasks/         # Acciones Screenplay (Login, Agregar al carrito)
│ │ ├─ ui/            # Mapeo de pantallas (Targets)
│ │ └─ utils/         # Utilidades generales (config, helpers)
│ └─ resources/
│ ├─ capabilities/    # Configuración Appium (Android / iOS)
│ ├─ environments/    # Configuración de entornos Serenity
│ ├─ features/        # Escenarios BDD (Cucumber)
│ ├─ log4j2.xml       # Configuración de logs
│ └─ serenity.conf    # Configuración global Serenity
├─ target/            # Reportes y compilación
├─ pom.xml            # Dependencias Maven
├─ build.gradle       # (opcional) Migración a Gradle
└─ README.md
```

---

## Configuración de Capabilities

### Archivo: `src/test/resources/capabilities/android-capabilities.json`

```json
{
  "platformName": "Android",
  "automationName": "UiAutomator2",
  "deviceName": "Pixel9TEST",
  "avd": "Pixel9TEST",
  "platformVersion": "14",
  "app": "apps/android/demostore.apk",
  "appPackage": "com.saucelabs.mydemoapp.android",
  "appActivity": "com.saucelabs.mydemoapp.android.view.activities.SplashActivity",
  "appWaitActivity": "com.saucelabs.mydemoapp.android.view.activities.*",
  "noReset": true,
  "fullReset": false,
  "unicodeKeyboard": true,
  "resetKeyboard": true,
  "uiautomator2ServerLaunchTimeout": 90000,
  "adbExecTimeout": 60000,
  "androidInstallTimeout": 90000,
  "newCommandTimeout": 120
}
```

### Archivo: `src/test/resources/capabilities/ios-capabilities.json`

```json
{
  "platformName": "iOS",
  "automationName": "XCUITest",
  "deviceName": "iPhone 15 Pro",
  "platformVersion": "17.0",
  "app": "apps/ios/demostore.app",
  "bundleId": "com.saucelabs.mydemoapp",
  "noReset": true,
  "autoAcceptAlerts": true,
  "newCommandTimeout": 120,
  "wdaLaunchTimeout": 90000
}
```

### Notas Importantes sobre Capabilities

#### Rutas de Aplicaciones

**Opción 1: Ruta relativa (Recomendado)**
```json
"app": "apps/android/demostore.apk"
```
- Se resuelve automáticamente al directorio del proyecto
- Multiplataforma (funciona en Windows, macOS, Linux)

**Opción 2: Ruta absoluta**
```json
"app": "D:/app_test/demostore.apk"
```
- Usar solo si la app está fuera del proyecto
- Cambiar `\` por `/` en Windows

**Opción 3: URL remota**
```json
"app": "https://github.com/saucelabs/my-demo-app-android/releases/download/v1.3.0/Android-MyDemoAppRN.apk"
```
- Appium descarga automáticamente

#### El Framework Automáticamente:

- ?Normaliza rutas relativas a absolutas
- ?Valida que el archivo exista (para rutas locales)
- ?Permite sobrescribir capabilities via `-D` properties
- ?Imprime las capabilities finales en consola

---

## Configuración de Serenity

### Archivo: `src/test/resources/serenity.conf`

```hocon
# =========================
# Serenity BDD - Config
# =========================
serenity {
  project.name = "mobile-testing-project"
  outputDirectory = "target/serenity"
  
  # Configuración de screenshots
  take.screenshots = AFTER_EACH_STEP
  # Otras opciones:
  # take.screenshots = FOR_FAILURES
  # take.screenshots = FOR_EACH_ACTION
  # take.screenshots = DISABLED
  
  logging = VERBOSE
}

# =========================
# WebDriver Configuration
# =========================
webdriver {
  driver = appium
  autodownload = false
  
  # URL de Appium Server
  remote.url = "http://127.0.0.1:4723"
  
  # Timeouts
  timeouts {
    implicitlywait = 10000
    fluentwait = 10000
  }
}

# =========================
# Appium Hub Configuration
# =========================
appium {
  hub = "http://127.0.0.1:4723"
}

# =========================
# Environment por defecto
# =========================
environment = android

# =========================
# Capabilities Configuration
# =========================
# Define las rutas de los archivos de capabilities
capabilities {
  # Ruta base donde están los archivos JSON
  base.path = "/capabilities/"
  
  # Archivos de capabilities por plataforma
  android.file = "android-capabilities.json"
  ios.file = "ios-capabilities.json"
  
  # Puedes agregar más configuraciones
  # android.tablet.file = "android-tablet-capabilities.json"
  # ios.ipad.file = "ios-ipad-capabilities.json"
}

# =========================
# Environments (Override capabilities)
# =========================
# Aquí puedes hacer overrides o agregar capabilities adicionales
environments {
  android {
    capabilities.file = ${capabilities.android.file}
    
    # Capabilities adicionales o overrides (opcionales)
    # Se mezclan con las del JSON
    additional.capabilities {
      # Ejemplo: Si quieres sobrescribir algo del JSON
      # "appium:deviceName" = "OtroDispositivo"
    }
  }
  
  ios {
    capabilities.file = ${capabilities.ios.file}
    
    # Capabilities adicionales o overrides (opcionales)
    additional.capabilities {
      # Ejemplo de capability adicional solo para iOS
      # "appium:wdaLaunchTimeout" = 60000
    }
  }
  
  # Ejemplo: Configuración para tablet Android
  android-tablet {
    capabilities.file = "android-tablet-capabilities.json"
    additional.capabilities {
      # Capabilities específicas para tablet
    }
  }
}
```

---

## Ejecución de Pruebas

### Paso 1: Iniciar Appium Server

Abre una terminal y ejecuta:

```bash
appium
```

**Salida esperada:**
```
[Appium] Welcome to Appium v2.11.5
[Appium] Non-default server args:
[Appium] {
[Appium]   allowInsecure: [ 'chromedriver_autodownload' ]
[Appium] }
[Appium] Attempting to load driver uiautomator2...
[Appium] Requiring driver at /Users/.../.appium/node_modules/appium-uiautomator2-driver
[Appium] AndroidUiautomator2Driver has been successfully loaded in 1.234s
[Appium] Appium REST http interface listener started on http://127.0.0.1:4723
[Appium] You can provide the following URLs in your client code to connect to this server:
[Appium] 	http://127.0.0.1:4723/ (only accessible from the same host)
[Appium] 	http://192.168.1.100:4723/
[Appium] Available drivers:
[Appium]   - uiautomator2@2.34.2 (automationName 'UiAutomator2')
[Appium]   - xcuitest@x.x.x (automationName 'XCUITest')
[Appium] No plugins have been installed. Use the "appium plugin" command to install the one(s) you want to use.
```

**Mantén esta terminal abierta durante la ejecución de tests.**

---

### Paso 2: Preparar Dispositivo/Emulador

#### 馃 Para Android

**Opción A: Crear y usar AVD (Emulador)**

```bash
# Crear AVD
avdmanager create avd \
  -n Pixel9TEST \
  -k "system-images;android-34;google_apis;x86_64" \
  -d pixel_9_pro

# Listar AVDs creados
emulator -list-avds

# Iniciar emulador
emulator -avd Pixel9TEST

# O iniciar sin audio (más rápido)
emulator -avd Pixel9TEST -no-audio -no-boot-anim
```

**Opción B: Usar dispositivo físico**

```bash
# 1. Habilitar "Opciones de Desarrollador" en el dispositivo:
#    - Configuración > Acerca del teléfono
#    - Tocar 7 veces "Número de compilación"

# 2. Habilitar "Depuración USB"
#    - Configuración > Opciones de Desarrollador > Depuración USB

# 3. Conectar dispositivo por USB

# 4. Verificar conexión
adb devices

# Salida esperada:
# List of devices attached
# ABC123XYZ       device
```

**Instalar APK en el dispositivo:**

```bash
# Opción 1: Instalar nueva
adb install "apps/android/demostore.apk"

# Opción 2: Reinstalar (sobrescribir)
adb install -r "apps/android/demostore.apk"

# Verificar instalación
adb shell pm list packages | grep demostore
```

**Comandos útiles:**

```bash
# Ver activity actual
adb shell dumpsys window | findstr mCurrentFocus

# Lanzar app manualmente (para probar)
adb shell am start -n com.saucelabs.mydemoapp.android/com.saucelabs.mydemoapp.android.view.activities.SplashActivity

# Cerrar app
adb shell am force-stop com.saucelabs.mydemoapp.android

# Desinstalar app
adb uninstall com.saucelabs.mydemoapp.android
```

#### Para iOS (Solo macOS)

**Listar simuladores disponibles:**

```bash
xcrun simctl list devices
```

**Abrir simulador:**

```bash
# Opción 1: Abrir app Simulator
open -a Simulator

# Opción 2: Boot específico
xcrun simctl boot "iPhone 15 Pro"
```

**Instalar app en simulador:**

```bash
xcrun simctl install booted apps/ios/demostore.app
```

---

### Paso 3: Ejecutar Tests

#### Ejecución Básica

**Ejecutar TODAS las pruebas (Android):**
```bash
mvn clean verify -Denvironment=android
```

**Ejecutar TODAS las pruebas (iOS):**
```bash
mvn clean verify -Denvironment=ios
```

#### Ejecución por Tags

**Solo pruebas de login en Android:**
```bash
mvn clean verify -Denvironment=android -Dtags="@loginandroid"
```

**Solo pruebas de productos:**
```bash
mvn clean verify -Denvironment=android -Dtags="@products"
```

**Múltiples tags (AND):**
```bash
mvn clean verify -Denvironment=android -Dtags="@android and @smoke"
```

**Múltiples tags (OR):**
```bash
mvn clean verify -Denvironment=android -Dtags="@login or @products"
```

**Excluir tags:**
```bash
mvn clean verify -Denvironment=android -Dtags="@android and not @wip"
```

#### Ejecución Rápida (Sin reporte)

**Solo ejecutar tests (más rápido):**
```bash
mvn clean test -Denvironment=android -Dtags="@loginandroid"
```

**Generar reporte después:**
```bash
mvn serenity:aggregate
```

#### Sobrescribir Capabilities

**Desde línea de comandos:**

```bash
# Cambiar dispositivo
mvn clean verify -Denvironment=android \
  -Dappium.deviceName="Pixel8" \
  -Dappium.platformVersion="13"

# Cambiar app
mvn clean verify -Denvironment=android \
  -Dappium.app="D:/otros/otra-app.apk"

# Múltiples overrides
mvn clean verify -Denvironment=android \
  -Dappium.deviceName="Samsung" \
  -Dappium.platformVersion="12" \
  -Dappium.noReset=false
```

---

## Reportes

### 1. Generar Reporte Serenity

**Opción A: Durante `verify` (automático)**
```bash
mvn clean verify -Denvironment=android
```

**Opción B: Generar reporte manualmente**
```bash
# Después de ejecutar tests
mvn serenity:aggregate
```

**Opción C: Test + Reporte (si falló verify)**
```bash
mvn clean test serenity:aggregate -Denvironment=android
```

### 2. Ver Reporte

**Abrir en navegador:**

```bash
# Windows
start target/site/serenity/index.html

# macOS
open target/site/serenity/index.html

# Linux
xdg-open target/site/serenity/index.html
```

**O navega manualmente a:**
```
demo_mobile/target/site/serenity/index.html
```


## Comandos 脷tiles

### Maven

```bash
# Limpiar proyecto
mvn clean

# Compilar sin ejecutar tests
mvn compile -DskipTests

# Instalar dependencias
mvn clean install -DskipTests

# Ver árbol de dependencias
mvn dependency:tree

# Ver dependencias desactualizadas
mvn versions:display-dependency-updates
```

### ADB (Android Debug Bridge)

```bash
# Listar dispositivos conectados
adb devices

# Instalar APK
adb install path/to/app.apk

# Reinstalar (mantiene datos)
adb install -r path/to/app.apk

# Desinstalar app
adb uninstall com.package.name

# Ver logs en tiempo real
adb logcat

# Ver activity actual
adb shell dumpsys window | findstr mCurrentFocus  # Windows
adb shell dumpsys window | grep mCurrentFocus     # macOS/Linux

# Lanzar activity
adb shell am start -n com.package.name/.ActivityName

# Detener app
adb shell am force-stop com.package.name

# Tomar screenshot
adb shell screencap -p /sdcard/screenshot.png
adb pull /sdcard/screenshot.png

# Reiniciar adb server
adb kill-server
adb start-server
```

### Appium

```bash
# Iniciar Appium
appium

# Iniciar en puerto específico
appium --port 4724

# Ver logs detallados
appium --log-level debug

# Listar drivers instalados
appium driver list

# Actualizar driver
appium driver update uiautomator2

# Instalar Appium Inspector (GUI)
npm install -g appium-inspector
```

### Emulador Android

```bash
# Listar AVDs disponibles
emulator -list-avds

# Iniciar emulador
emulator -avd Pixel9TEST

# Iniciar sin audio (más rápido)
emulator -avd Pixel9TEST -no-audio

# Borrar datos del emulador
emulator -avd Pixel9TEST -wipe-data
```

---

## Troubleshooting

### Problema 1: "Cannot find Android SDK"

**Solución:**

**Windows:**
```cmd
setx ANDROID_HOME "C:\Users\TuUsuario\AppData\Local\Android\Sdk"
setx PATH "%PATH%;%ANDROID_HOME%\platform-tools"
```

**macOS/Linux:**
```bash
export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$ANDROID_HOME/platform-tools:$PATH
source ~/.bash_profile
```

---

### Problema 2: "Appium server not responding"

**Solución:**

```bash
# Verificar que Appium esté corriendo
appium

# Verificar puerto 4723
netstat -ano | findstr 4723  # Windows
lsof -i :4723                # macOS/Linux

# Reiniciar Appium
appium --port 4724
```

---

### Problema 3: "Element not found"

**Solución:**

1. Usar Appium Inspector para verificar locators
2. Aumentar implicit wait en capabilities
3. Agregar explicit waits en el código

```bash
npm install -g appium-inspector
appium-inspector
```

---

### Problema 4: "Session not created"

**Causas comunes:**
- App no encontrada o ruta incorrecta
- Capabilities incorrectas
- Driver no instalado

**Solución:**

```bash
# Verificar capabilities
cat src/test/resources/capabilities/android-capabilities.json

# Verificar app existe
ls apps/android/demostore.apk

# Verificar drivers
appium driver list
```

---

## Recursos Adicionales

### Documentación Oficial

- **Serenity BDD:** https://serenity-bdd.github.io/
- **Appium:** https://appium.io/docs/en/latest/
- **Selenium:** https://www.selenium.dev/documentation/
- **Cucumber:** https://cucumber.io/docs/cucumber/

### Herramientas 脷tiles

- **Appium Inspector:** https://github.com/appium/appium-inspector
- **Android Studio:** https://developer.android.com/studio
- **Xcode (iOS):** https://developer.apple.com/xcode/


---

**Happy Testing!**
