package io.github.im2back.credit.appraiser.model.assessmentdto;

import java.util.List;

import io.github.im2back.credit.appraiser.model.carddtos.CardApprovedDto;

public record ResultAssessmentClientResponseDto(
		
	List<CardApprovedDto>cards
		
		) {

}
