package model.enums;

import java.util.Arrays;

public enum Gender {
    MASCHIO("Maschio"),
    FEMMINA("Femmina"),
    ALTRO("Altro");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public static String[] getValues() {
        return Arrays.stream(
                        Gender.class.getEnumConstants())
                .map(Enum::toString)
                .toArray(String[]::new
                );
    }

    @Override
    public String toString() {
        return this.gender;
    }
}
