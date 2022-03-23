package net.tutorials_app.dto;

import java.util.ArrayList;
import java.util.List;

import net.tutorials_app.dto.request.TutorialRequestDto;
import net.tutorials_app.dto.response.TutorialResponseDto;
import net.tutorials_app.model.Tutorial;

public class Mapper {

	public TutorialResponseDto tutorialToTutorialResponseDto(Tutorial tutorial) {
		
		TutorialResponseDto tutorialResponseDto = new TutorialResponseDto();
		
		tutorialResponseDto.setTitle(tutorial.getTitle());
		tutorialResponseDto.setDescription(tutorial.getDescription());
		tutorialResponseDto.setPublished(tutorial.getPublished());
		
		return tutorialResponseDto;
	}
	
	public Tutorial tutorialRequestDtoToTutorial(TutorialRequestDto tutorialRequestDto) {
		
		return new Tutorial(
				tutorialRequestDto.getTitle(),
				tutorialRequestDto.getDescription(),
				tutorialRequestDto.getPublished());
	}
	
	public List<TutorialResponseDto> tutorialsToTutoriaslResponseDtos(List<Tutorial> tutorials) {
		
		List<TutorialResponseDto> tutorialResponseDtos = new ArrayList<>();
		
		for(Tutorial tutorial : tutorials) 
			tutorialResponseDtos.add(tutorialToTutorialResponseDto(tutorial));
		
		return tutorialResponseDtos;
	}
	
}
