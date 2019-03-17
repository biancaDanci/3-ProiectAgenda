package agenda.model.base;

import java.time.Duration;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import agenda.model.repository.interfaces.RepositoryContact;

public class Activity {
	private String name;
	private Date start;
	private Duration duration;
	private String loc;
	private List<Contact> contacts;
	private String description;

	public void setName(String name) {
		this.name = name;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Activity(String name, Date start, Duration duration, List<Contact> contacts,
					String description, String loc) {
		this.name = name;
		this.description = description;
		if (contacts == null)
			this.contacts = new LinkedList<Contact>();
		else
			this.contacts = new LinkedList<Contact>(contacts);

		this.start = new Date();
		this.start.setTime(start.getTime());
		this.duration =  duration;
		this.loc = loc;
	}

	public String getName() {
		return name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Activity))
			return false;
		Activity act = (Activity) obj;
		if (act.description.equals(description) && start.equals(act.start)
				&& duration.equals(act.duration) && name.equals(act.name))
			return true;
		return false;
	}

	public boolean intersect(Activity act) {
		//if (start.compareTo(act.duration) < 0
		//		&& act.start.compareTo(duration) < 0)
		//	return true;
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append("#");
		sb.append(start.getTime());
		sb.append("#");
		sb.append(duration.toString());
		sb.append("#");
		sb.append(description);
		sb.append("#");
		sb.append(loc);
		sb.append("#");
		for (Contact c : contacts) {
			sb.append("#");
			sb.append(c.getName());
		}
		return sb.toString();
	}

	public static Activity fromString(String line, RepositoryContact repcon) {
		try {
			String[] str = line.split("#");
			String name = str[0];
			Date start = new Date(Long.parseLong(str[1]));
			Duration duration = Duration.ofHours(Long.parseLong(str[2]));
			String description = str[3];
			String loc = str[4];
			List<Contact> conts = new LinkedList<Contact>();
			for (int i = 5; i < str.length; i++) {
				conts.add(repcon.getByName(str[i]));
			}
			System.out.println("start" + start);
			System.out.println("duration" + duration);
			return new Activity(name, start, duration, conts, description,loc);
		} catch (Exception e) {
			return null;
		}
	}
}
