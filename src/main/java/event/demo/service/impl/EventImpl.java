package event.demo.service.impl;

import event.demo.dao.EventMapper;
import event.demo.domain.Event;
import event.demo.domain.EventExample;
import event.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventImpl implements EventService {
    @Autowired
    private EventMapper eventMapper;

    @Override
    public Boolean insert(Event event){
        try {
            event.setGmtCreate(new Date());
            event.setGmtModified(new Date());
            if (eventMapper.insertSelective(event)==1){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public int delete(String eventId){
        try {
            EventExample example = new EventExample();
            EventExample.Criteria criteria = example.createCriteria();
            criteria.andEventIdEqualTo(eventId);
            return eventMapper.deleteByExample(example);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int update(Event event){
        try {
            EventExample example = new EventExample();
            EventExample.Criteria criteria = example.createCriteria();
            criteria.andEventIdEqualTo(event.getEventId());
            return eventMapper.updateByExampleSelective(event, example);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Event> selectByUrl(String url){
        try {
            EventExample example = new EventExample();
            EventExample.Criteria criteria = example.createCriteria();
            criteria.andUrlEqualTo(url);
            List<Event> list = eventMapper.selectByExample(example);
            if (list.size()!=0){
                return list;
            }else {
                return null;
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
