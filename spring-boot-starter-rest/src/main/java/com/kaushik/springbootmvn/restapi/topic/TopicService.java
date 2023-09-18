package com.kaushik.springbootmvn.restapi.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

	  @Autowired // This means to get the bean called userRepository // Which is auto-generated by Spring, we will use it to handle the data
	  private TopicRepository topicRepo;
	  
//	List<Topic> topics=new ArrayList<>( 
//			Arrays.asList(new Topic("1","2","3")) 
//			);
	
	public List<Topic>  getAllTopics(){
//		return topics;
		List<Topic> topics=new ArrayList<>();
		topicRepo.findAll().forEach(topics::add);
		return topics;
	}
	

	public void  addTopic(Topic topic){
//		 topics.add(topic);
		topicRepo.save(topic);
	}

	public Optional<Topic>  getTopic(String id){
//		return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		return topicRepo.findById(id);
	}


	public void updateTopic(Topic topic, String id) {
//		for(int i=0;i<topics.size();i++) {
//			Topic t=topics.get(i);
//			if(t.getId().equals(id)) {
//				topics.set(i, topic);
//				return;
//			}
//		}
		topicRepo.save(topic);
	}


	public void deleteTopic(String id) {
//			topics.removeIf(t -> t.getId().equals(id));
		topicRepo.deleteById(id);
	}
}