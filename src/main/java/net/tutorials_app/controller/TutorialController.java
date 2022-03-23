package net.tutorials_app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.tutorials_app.dto.response.TutorialResponseDto;
import net.tutorials_app.service.TutorialService;

@CrossOrigin
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
			List<TutorialResponseDto> tutorials = tutorialService.getAllTutorials();
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
