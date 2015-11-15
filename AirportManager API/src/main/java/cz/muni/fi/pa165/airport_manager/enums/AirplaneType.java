package cz.muni.fi.pa165.airport_manager.enums;

/**
 * Definition of enums for Airplane type
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
public enum AirplaneType {

    ECONOMY {
        public String toString() {
            return "Economy"; }
    },
          
    BUSINESS {
        public String toString() {
            return "Business"; }
    },

    FIRST {
        public String toString() {
            return "First"; }
    }

}