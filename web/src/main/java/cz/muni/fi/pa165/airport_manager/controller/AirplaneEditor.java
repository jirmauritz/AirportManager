package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.facade.AirplaneFacade;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Jiri Mauritz
 */
public class AirplaneEditor extends PropertyEditorSupport {
	
	private final AirplaneFacade airplaneFacade;

	public AirplaneEditor(AirplaneFacade airplaneFacade) {
		this.airplaneFacade = airplaneFacade;
	}
	
	/**
	 * Expected string to parse is [id name ...] and we use id to fetch
	 * airplane from db.
	 * 
	 * @param text - string representing airplane in UI
	 * @throws IllegalArgumentException 
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		String[] arrayString = text.split(" ");
		String id = arrayString[0];
		AirplaneDTO airplane = airplaneFacade.getAirplane(Long.parseLong(id));
		setValue(airplane);
	}
	
}
