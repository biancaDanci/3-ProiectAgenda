package agenda.model.repository.classes;


import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import agenda.model.base.Activity;
import agenda.model.repository.interfaces.RepositoryActivity;

public class RepositoryActivityMock implements RepositoryActivity {

	private List<Activity> activities;
	
	public RepositoryActivityMock()
	{
		activities = new LinkedList<Activity>();
//		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
//		try {
//			Activity act = new Activity(df.parse("03/20/2013 12:00"), 
//					df.parse("03/20/2013 14:00"),
//					null,
//					"Meal break",
//					"Memo");
//			activities.add(act);
//			act = new Activity(df.parse("03/21/2013 12:00"), 
//					df.parse("03/21/2013 14:00"),
//					null,
//					"Meal break",
//					"Memo");
//			activities.add(act);
//			act = new Activity(df.parse("03/22/2013 12:00"), 
//					df.parse("03/22/2013 14:00"),
//					null,
//					"Meal break",
//					"Memo");
//			activities.add(act);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
	}
	
	@Override
	public List<Activity> getActivities() {
		return new LinkedList<Activity>(activities);
	}

	@Override
	public boolean addActivity(Activity activity) {
		int  i = 0;
		boolean conflicts = false;
		Calendar cal = Calendar.getInstance();
		if (activity.getName().length()==0)
			return false;
		else if (activity.getDescription().length()==0)
			return false;
		while( i < activities.size() )
		{
			cal.setTime(activity.getStart());
			cal.add(Calendar.HOUR_OF_DAY, (int) activity.getDuration().toHours());
			Date end_activity = cal.getTime();

			cal.setTime(activities.get(i).getStart());
			cal.add(Calendar.HOUR_OF_DAY, (int) activities.get(i).getDuration().toHours());
			Date end_activities = cal.getTime();
			if(activity.getStart().compareTo(end_activities)<=0 &&
					end_activity.compareTo(activities.get(i).getStart())>=0)

				conflicts = true;

			i++;
		}
		if ( !conflicts )
		{
			activities.add(activity);
			return true;
		}
		else return false;

	}

	@Override
	public boolean removeActivity(Activity activity) {
		int index = activities.indexOf(activity);
		if (index<0) return false;
		activities.remove(index);
		return true;
	}

	@Override
	public boolean saveActivities() {
		return true;
	}

	@Override
	public int count() {
		return activities.size();
	}

	@Override
	public List<Activity> activitiesByName(String name) {
		List<Activity> result = new LinkedList<Activity>();
		for (Activity a : activities)
			if (a.getName().equals(name)) result.add(a);
		return result;
	}

	@Override
	public List<Activity> activitiesOnDate(String name, Date d) {
		List<Activity> result = new LinkedList<Activity>();
		for (Activity a : activities)
			if (a.getName().equals(name)) {
				if (a.getStart().compareTo(d) <= 0) result.add(a);
			}
				return result;
	}

}
