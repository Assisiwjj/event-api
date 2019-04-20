package event.demo.web;

import event.demo.domain.Event;
import event.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("event")
public class EventController extends BaseController{
    @Autowired
    private EventService eventService;


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Map<String, Object> insertEvent(@RequestBody Event event) {
        msg.clear();
        try {
            if (event.getProjectName()!=null && event.getEvent()!= null && event.getEventId()!=null
                    && event.getAttr()!=null && event.getUrl()!=null &&event.getProperties()!=null
                    && event.getProjectId()!=null){
                if (eventService.insert(event) == true) {
                    msg.put("code", "1");
                    msg.put("msg", "成功");
                } else {
                    msg.put("code", "0");
                    msg.put("msg", "失败");
                }
            } else {
                msg.put("code", "0");
                msg.put("msg", "传参不正确");
            }
        } catch (Exception e) {
            msg.put("code", "0");
            msg.put("msg", e.getMessage());
        }
        return msg;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Map<String, Object> deleteEvent(HttpServletRequest request) {
        msg.clear();
        try{
            String eventId=request.getParameter("eventId");
            if (eventId != null) {
                if (eventService.delete(eventId) == 1) {
                    msg.put("code", "1");
                    msg.put("msg", "成功");
                } else {
                    msg.put("code", "0");
                    msg.put("msg", "失败");
                }
            } else {
                msg.put("code", "0");
                msg.put("msg", "eventId为空");
            }
        }catch (Exception e){
            msg.put("code","0");
            msg.put("msg", e.getMessage());
        }
        return msg;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<String, Object> updateEvent(@RequestBody Event event) {
        msg.clear();
        try{
            if (event.getEventId() != null) {
                if (eventService.update(event) == 1) {
                    msg.put("code", "1");
                    msg.put("msg", "成功");
                } else {
                    msg.put("code", "0");
                    msg.put("msg", "失败");
                }
            } else {
                msg.put("code", "0");
                msg.put("msg", "eventId为空");
            }
        }catch (Exception e){
            msg.put("code","0");
            msg.put("msg", e.getMessage());
        }
        return msg;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Map<String, Object> selectByUrl(HttpServletRequest request) {
        msg.clear();
        try{
            String url=request.getParameter("url");
            if (url != null) {
                List<Event> list = eventService.selectByUrl(url);
                if (list!=null) {
                    msg.put("code", "1");
                    msg.put("msg", "成功");
                    msg.put("data", list);
                }else {
                    msg.put("code", "0");
                    msg.put("msg", "数据为空");
                }
            } else {
                msg.put("code", "0");
                msg.put("msg", "url为空");
            }
        }catch (Exception e){
            msg.put("code","0");
            msg.put("msg", e.getMessage());
        }
        return msg;
    }
}
