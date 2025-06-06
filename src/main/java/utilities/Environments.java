package utilities;

/**
 * Created by Asli Ekmekci
 */
public enum Environments {

    PRODUCTION("production"),
    STAGE("stage");

    private final String name;

    Environments(String name) {
        this.name = name;
    }

    public static Environments fromString(String value) {
        for (Environments env : Environments.values()) {
            if (env.name.equalsIgnoreCase(value)) {
                return env;
            }
        }

        return STAGE;
    }

    public String getName() {
        return name;
    }
}

