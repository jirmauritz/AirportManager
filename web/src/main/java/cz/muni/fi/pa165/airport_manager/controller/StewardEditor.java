package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.dto.StewardSimpleDTO;
import cz.muni.fi.pa165.airport_manager.facade.StewardFacade;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author Jiri Mauritz
 */
public class StewardEditor extends PropertyEditorSupport {
	
	private final StewardFacade stewardFacade;

	public StewardEditor(StewardFacade stewardFacade) {
		this.stewardFacade = stewardFacade;
	}
	
	/**
	 * Expected string to parse is [id name ...] and we use id to fetch
	 * steward from db.
	 * 
	 * @param text - string representing steward in UI
	 * @throws IllegalArgumentException 
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		String[] arrayString = text.split(" ");
		String id = arrayString[0];
		StewardSimpleDTO steward = stewardFacade.getSteward(Long.parseLong(id));
		setValue(steward);
	}
	
}
