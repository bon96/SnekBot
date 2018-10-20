package client.hooks;

/**
 * BonBom
 * Date: 3.11.2017
 * Time: 17.01
 */

public class RSFieldInfo {
    public String realClassName;
    public String realFieldName;
    public String classname;
    public String fieldName;
    public Number multiplier;

    public <U extends Number> RSFieldInfo(String realClassName, String realFieldName, String className, String fieldName, U multiplier) {
        this.realClassName = realClassName;
        this.realFieldName = realFieldName;
        this.classname = className;
        this.fieldName = fieldName;
        this.multiplier = multiplier;
    }

    public static void main(String[] args) {

    }

}
