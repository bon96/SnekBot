package client.hooks;

/**
 * BonBom
 * Date: 3.11.2017
 * Time: 20.54
 */

public class RSClass {
    public HookLoader hookLoader;
    public String className;
    public String originalClassName;

    RSClass(HookLoader hookLoader, String className, String originalClassName) {
        this.hookLoader = hookLoader;
        this.className = className;
        this.originalClassName = originalClassName;
    }

    public Class getValue() {
        try {
            return hookLoader.classLoader.loadClass(originalClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
