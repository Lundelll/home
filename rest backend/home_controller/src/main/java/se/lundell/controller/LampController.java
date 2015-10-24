package se.lundell.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.lundell.rooms.Room;

@RestController
public class LampController {
	
	private static final Logger log = LoggerFactory.getLogger(LampController.class);
	private static final String testAnswer = "Lamps in the %s is turned %s!";
	
	@RequestMapping("/livingroom")
	public Room livingRoom(@RequestParam(value="switch", defaultValue="0") boolean state) {
		String onOrOff = state ? "on":"off";
		log.info("The livingroom lamps are now turned " + onOrOff);
		return new Room("livingroom", String.format(testAnswer, "livingroom", onOrOff), state);
	}
	
	@RequestMapping("/bedroom")
	public Room bedRoom(@RequestParam(value="switch", defaultValue="0") boolean state) {
		String onOrOff = state ? "on":"off";
		log.info("The bedroom lamps are now turned " + onOrOff);
		return new Room("bedroom", String.format(testAnswer, "bedroom", onOrOff), state);
	}
}
