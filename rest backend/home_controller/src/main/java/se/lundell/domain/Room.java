package se.lundell.domain;

public class Room {

	private String name;
	private String response;
	private boolean state;
	
	public Room(String name, String response, boolean state) {
		this.name = name;
		this.response = response;
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
}
