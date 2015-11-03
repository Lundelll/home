package se.lundell.web;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.ezand.telldus.cli.repository.CliRepository;
import org.ezand.telldus.core.domain.Device;
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
	private static boolean on = false;
	
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
	public @ResponseBody Room toggleRoomLight(@PathVariable("roomId") int roomId) {
		log.info("Lamp in room with id: " + roomId + " has been turned on or off");
		List<Device> devices = clientTelldusRepository.getDevices();
		
		for(Device d : devices) {
			System.out.println(d.toString());
		}
		
		clientTelldusRepository.turnDeviceOff(2);
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		clientTelldusRepository.turnDeviceOn(2);
		
		return new Room("bedroom", String.format(testAnswer, "bedroom", "on or off"), true);
	}
}