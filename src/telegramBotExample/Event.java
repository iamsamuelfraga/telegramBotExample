package telegramBotExample;

import java.util.Date;

public class Event {

	private Date date;
	private String user;
	private String type;

	public Event(Date date, String user, String type) {

		this.date = date;
		this.user = user;
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
