package com.empresa.project.json.nested;

/**
 * Configuración de una aplicación, con un objeto anidado.
 *
 * Una clase dentro de otra (Settings) se convierte en un OBJETO ANIDADO en el JSON:
 * {
 *   "appName": "Demo App",
 *   "version": "1.0",
 *   "settings": { "theme": "Light", "notifications": true, "language": "es" }
 * }
 *
 * Settings es "static" para que Gson pueda crearla sin necesitar una instancia
 * de AppConfig; una clase interna NO estática da problemas al leer el JSON.
 * Los campos son públicos para mantener el ejemplo simple.
 */
public class AppConfig {

    public String appName;
    public String version;
    public Settings settings;

    public static class Settings {
        public String theme;
        public boolean notifications;
        public String language;

        @Override
        public String toString() {
            return "Settings[theme=" + theme + ", notifications=" + notifications + ", language=" + language + "]";
        }
    }

    @Override
    public String toString() {
        return "AppConfig[appName=" + appName + ", version=" + version + ", settings=" + settings + "]";
    }
}
