package online.codevault.java.utilities.textfileformats.fixedformat;

public enum TestEnum {

    OPTION_A("AA"),
    OPTION_B("BB");

    private final String value;

    TestEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
