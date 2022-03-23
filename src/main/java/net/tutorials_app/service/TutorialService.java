package net.tutorials_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tutorials_app.dto.Mapper;
import net.tutorials_app.dto.request.TutorialRequestDto;
import net.tutorials_app.dto.response.TutorialResponseDto;
import net.tutorials_app.model.Tutorial;
import net.tutorials_app.repository.TutorialRepository;

@Service
public class TutorialService {

	@Autowired
	private final TutorialRepository tutorialRepository;

	private Mapper mapper = new Mapper();

	public TutorialService(TutorialRepository tutorialRepository) {
		this.tutorialRepository = tutorialRepository;
	}

	public List<TutorialResponseDto> getAllTutorials() {

		List<Tutorial> tutorials = tutorialRepository.findAll();
		return mapper.tutorialsToTutoriaslResponseDtos(tutorials);
	}

	public TutorialResponseDto getTutorialById(final Long id) {

		Tutorial tutorial = tutorialRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Tutorial with " + id + " not found"));

		return mapper.tutorialToTutorialResponseDto(tutorial);
	}

	public void createTutorial(final TutorialRequestDto tutorialRequestDto) {

		Tutorial tutorial = mapper.tutorialRequestDtoToTutorial(tutorialRequestDto);
		tutorialRepository.save(tutorial);

	}

	public void updateTutorial(final Long id, final TutorialRequestDto tutorialRequestDto) {

		Tutorial tutorial = tutorialRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Tutorial with " + id + " not found"));

		tutorial.setTitle(tutorialRequestDto.getTitle());
		tutorial.setDescription(tutorialRequestDto.getDescription());
		tutorial.setPublished(tutorialRequestDto.getPublished());

		tutorialRepository.save(tutorial);
	}

	public void deleteTutorial(final Long id) {

		boolean exist = tutorialRepository.existsById(id);

		if (!exist)
			throw new IllegalArgumentException("Tutorial with " + id + " not found");
		else
			tutorialRepository.deleteById(id);

	}

	public void deleteAllTutorials() {
		tutorialRepository.deleteAll();
	}

	public List<TutorialResponseDto> findByPublished() {

		return getAllTutorials().stream().filter(tutorial -> tutorial.getPublished().equals(true))
				.collect(Collectors.toList());

	}
	
	public List<TutorialResponseDto> findByTitleContaining(String title) {
		
		List<TutorialResponseDto> dtos = mapper
				.tutorialsToTutoriaslResponseDtos(tutorialRepository.findByTitleContaining(title));
		return dtos;
	}
}
