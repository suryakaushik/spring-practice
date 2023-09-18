package com.kaushik.springbootmvn.restapi.topic;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	@Autowired
    private TopicService ts;
	  
    @RequestMapping("/topics")
    public List<Topic> getAllTopics() {
    	return ts.getAllTopics();
    }

    @RequestMapping("/topics/{passedId}")
	public Optional<Topic> getTopic(@PathVariable("passedId") String id){
		return ts.getTopic(id);
	}

    @RequestMapping(method=RequestMethod.POST,value="/topics")
	public void addTopic(@RequestBody Topic topic){
		 ts.addTopic(topic);
	}

    @RequestMapping(method=RequestMethod.PUT,value="/topics/{passedId}")
	public void updateTopic(@RequestBody Topic topic,@PathVariable("passedId") String id){
		 ts.updateTopic(topic,id);
	}

    @RequestMapping(method=RequestMethod.DELETE,value="/topics/{passedId}")
	public void deleteTopic(@PathVariable("passedId") String id){
    	 ts.deleteTopic(id);
	}
}
