package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

@Controller
public class AlienController {
	@Autowired
	AlienRepo alienRepo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

	@RequestMapping("/addAlien")
	public String addAlien(Alien alien) {
		alienRepo.save(alien);
		return "home.jsp";
	}

//	localhost:8080/getAlien?aid=102-->For HUMAN-SERVER COMMUNICATION
//	Crud Repository by default returns the objects.
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int aid) {
		ModelAndView mv=new ModelAndView("showAlien.jsp");
		Alien alien=alienRepo.findById(aid).orElse(new Alien());
		mv.addObject(alien);
		return mv;
	}
	

	
	//NOTE: SERVER DOES CONTENT NEGOTIATION. .i.e., User can request either JSON or XML data. (Client should add following header:  Accept: application/xml)
	// To support content-negotiation, add "jackson-dataformat-xml" dependency in the pom.xml

//	localhost:8080/getAlienNew/102-->For SERVER-SERVER COMMUNICATION(REST->JSON/SOAP->XML)
//	jackson jar file converts java objects to json object.
//	For REST, Use JpaRepository(Instead of CrudRepository), which returns Optional<any_object>
	@RequestMapping(path="/getAlienNew/{aid}",produces={"application/json"}) //It gives status 406 Error, if user requests xml data
	@ResponseBody //It ensures that we return string as an object, not as an html/jsp file
//	Instead of above 2 lines we can also use, @GetMapping(path="/getAlienNew/{aid}",produces={"application/json"}) and also add @RestController to this class
	public Optional<Alien> getAlienNew(@PathVariable("aid") int aid) {
		return alienRepo.findById(aid);
	}
	

	@PostMapping(path="/addAlienNew/",consumes= {"application/json"})
	public Alien addAlienNew(@RequestBody Alien alien) { //RequestBody ensures that we can even send raw-json data via postman
		alienRepo.save(alien);
		return alien;
	}
	

	@GetMapping(path="/getAllAliens")
	public List<Alien> getAllAliens() {
		return (List<Alien>) alienRepo.findAll();
	}
	

	@DeleteMapping(path="/deleteAlienNew/{aid}")
	public String deleteAlienNew(@PathVariable("aid") int aid) {
//		alienRepo.delete(alienRepo.getOne(aid));
		return "Deleted Successfully!";
	}

	@PutMapping(path="/updateAlienNew",consumes= {"application/json"})
	public Alien saveOrUpdateAlienNew(@RequestBody Alien alien) {
		alienRepo.save(alien);
		return alien;
	}
}
