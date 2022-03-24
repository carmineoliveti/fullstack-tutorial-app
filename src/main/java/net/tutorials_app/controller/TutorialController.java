package net.tutorials_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.tutorials_app.dto.request.TutorialRequestDto;
import net.tutorials_app.dto.response.TutorialResponseDto;
import net.tutorials_app.service.TutorialService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	private final TutorialService tutorialService;

	public TutorialController(TutorialService tutorialService) {
		this.tutorialService = tutorialService;
	}
	
	@GetMapping("/tutorials")
	public ResponseEntity<List<TutorialResponseDto>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<TutorialResponseDto> tutorials = tutorialService.getAllTutorials(title);
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 @GetMapping("/tutorials/{id}")
	 public ResponseEntity<TutorialResponseDto> getTutorialById(@PathVariable("id") Long id) {
		 
		 Optional<TutorialResponseDto> tutorial = Optional.of(tutorialService.getTutorialById(id));
		 if(tutorial.isPresent())
			 return new ResponseEntity<>(tutorial.get(), HttpStatus.OK);
		 else
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }
	 
	 @PostMapping("/tutorials")
	  public ResponseEntity<TutorialResponseDto> createTutorial(@RequestBody TutorialRequestDto tutorialRequestDto) {
		 try {
			 TutorialResponseDto tutorial = tutorialService.createTutorial(tutorialRequestDto);
			 return new ResponseEntity<>(tutorial, HttpStatus.CREATED);
		 } catch(Exception e) {
			 return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	 }
	 
	 @PutMapping("/tutorials/{id}")
	  public ResponseEntity<TutorialResponseDto> updateTutorial(@PathVariable("id") Long id, @RequestBody TutorialRequestDto tutorialRequestDto) {
		 
		 Optional<TutorialResponseDto> tutorialData = Optional.of(tutorialService.getTutorialById(id));
		 if(tutorialData.isPresent())
			 return new ResponseEntity<>(tutorialService.updateTutorial(id, tutorialRequestDto), HttpStatus.OK);
		 else
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 }
	 
	 @DeleteMapping("/tutorials/{id}")
	 public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Long id) {
		 try {
			 tutorialService.deleteTutorial(id);
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 } catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	 
	 @DeleteMapping("/tutorials")
	 public ResponseEntity<HttpStatus> deleteAllTutorials() {
		 try {
			 tutorialService.deleteAllTutorials();
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 } catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
	 
	 @GetMapping("/tutorials/published")
	 public ResponseEntity<List<TutorialResponseDto>> findByPublished() {
		 try {
			 List<TutorialResponseDto> tutorials = tutorialService.findByPublished(true);
			 
			 if(tutorials.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			 
			 return new ResponseEntity<>(tutorials, HttpStatus.OK);
		 } catch (Exception e) {
			 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
}
