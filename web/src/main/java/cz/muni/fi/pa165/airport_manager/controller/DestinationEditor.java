package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationSimpleDTO;
import cz.muni.fi.pa165.airport_manager.facade.DestinationFacade;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Jiri Mauritz
 */
public class DestinationEditor extends PropertyEditorSupport {
	
	private final DestinationFacade destinationFacade;

	public DestinationEditor(DestinationFacade destinationFacade) {
		this.destinationFacade = destinationFacade;
	}
	
	/**
	 * Expected string to parse is [id name ...] and we use id to fetch
	 * destination from db.
	 * 
	 * @param text - string representing destination in UI
	 * @throws IllegalArgumentException 
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		String[] arrayString = text.split(" ");
		String id = arrayString[0];
		DestinationSimpleDTO destination = destinationFacade.findById(Long.parseLong(id));
		setValue(destination);
	}
	
}
