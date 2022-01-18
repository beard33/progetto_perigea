package com.depa.progettinocovid.DataExtractor.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.depa.progettinocovid.DataExtractor.model.StatiClinici;
import com.depa.progettinocovid.DataExtractor.model.StatiCliniciDto;

@Mapper(componentModel = "spring")
public interface StatiCliniciMapper {
	StatiCliniciMapper INSTANCE = Mappers.getMapper(StatiCliniciMapper.class);
	
	StatiCliniciDto mapToDto(StatiClinici source);
	
	List<StatiCliniciDto> mapToDtoList(List<StatiClinici> source);
}
