package cz.muni.fi.pa165.airport_manager.config;

import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import org.dozer.DozerConverter;

/**
 * Converts Airport enum to string when mapping DTO
 * objects to persistence objects and vice versa.
 * 
 * @author Jiri Mauritz
 */
public class AirportTypeStringDozenConverter  extends DozerConverter<String, AirplaneType> {

	public AirportTypeStringDozenConverter() {
		super(String.class, AirplaneType.class);
	}
	
	@Override
	public AirplaneType convertTo(String a, AirplaneType b) {
		return AirplaneType.valueOf(a);
	}

	@Override
	public String convertFrom(AirplaneType b, String a) {
		return b.name();
	}

}
