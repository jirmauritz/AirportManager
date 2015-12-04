package cz.muni.fi.pa165.airport_manager.controller;

/**
 * Holds URIs to available paths. This class is non-instantiable.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public class ApiUris {
    private ApiUris() {throw new AssertionError("Do not instantiate");}

    public static final String URI_DESTINATION      = "/destination";
    public static final String URI_DESTINATION_ALL  = "/destination/list";
    public static final String URI_DESTINATION_VIEW = "/destination/view";

    //TODO rest of constants

}
