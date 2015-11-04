package se.lundell.web;

import org.ezand.telldus.cli.repository.CliRepository;
import org.ezand.telldus.core.domain.State;
import org.ezand.telldus.core.util.RichBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import se.lundell.domain.Room;

@RestController
@RequestMapping("/lamp/")
public class LampController {
	
	private static final Logger log = LoggerFactory.getLogger(LampController.class);
	private static final String testAnswer = "Lamps in the %s is turned %s!";
	
	CliRepository clientTelldusRepository = new CliRepository("/usr/bin/tdtool");
	
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
	
	@RequestMapping(value="/{roomId}", method=RequestMethod.GET)
	public @ResponseBody Room toggleRoomLight(@PathVariable("roomId") int roomId, @RequestParam(value="switch") boolean state) {
		
		if(state) {
			clientTelldusRepository.turnDeviceOn(roomId);
			log.info("Lamp: " + roomId + " is turned on.");
			return new Room("bedroom", String.format(testAnswer, "bedroom", "on"), true);
		} else {
			clientTelldusRepository.turnDeviceOff(roomId);
			log.info("Lamp: " + roomId + " is turned off.");
			return new Room("bedroom", String.format(testAnswer, "bedroom", "off"), true);
		}
//		List<Device> devices = clientTelldusRepository.getDevices();	
	}
}