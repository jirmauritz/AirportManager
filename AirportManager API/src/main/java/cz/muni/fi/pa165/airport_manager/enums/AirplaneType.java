package cz.muni.fi.pa165.airport_manager.enums;

/**
 * Definition of enums for Airplane type
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
public enum AirplaneType {

    ECONOMY("Economy"),
    BUSINESS("Business"),
    FIRST("First");

    private final String name;

    AirplaneType(String name) {
        this.name = name;
    }

    public static boolean isMember(String typeToCheck) {
        return AirplaneType.of(typeToCheck) != null;
    }

    public static AirplaneType of(String typeVal) {
        for (AirplaneType type : AirplaneType.values()) {
            if (type.toString().equals(typeVal)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
