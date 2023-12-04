package ci.inventory.dao.interfaces;


import java.util.List;

import ci.inventory.entity.Event_occur;

public interface IEvent_occurDao {
	Event_occur create(Event_occur event_occur);
	Event_occur getById(int id);
	Event_occur update(Event_occur event_occur);
	int delete(int id);
	List<Event_occur> getAll();
}
