package ci.inventory.services.interfaces;


import java.util.List;

import ci.inventory.entity.Event_occur;

public interface IEvent_occurService {
	Event_occur create(Event_occur event_occur);
	Event_occur get(int id);
	Event_occur update(Event_occur event_occur);
	int delete(int id);
	List<Event_occur> getAll();
}
