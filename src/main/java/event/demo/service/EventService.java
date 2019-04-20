package event.demo.service;

import event.demo.domain.Event;

import java.util.List;

public interface EventService {
    Boolean insert(Event event);

    int delete(String eventId);

    int update(Event event);

    List<Event> selectByUrl(String url);
}
