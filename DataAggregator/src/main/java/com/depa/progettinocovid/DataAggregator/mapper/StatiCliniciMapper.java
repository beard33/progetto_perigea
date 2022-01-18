package com.depa.progettinocovid.DataAggregator.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.depa.progettinocovid.DataAggregator.models.StatiClinici;
import com.depa.progettinocovid.DataAggregator.models.StatiCliniciDto;

@Mapper(componentModel = "spring")
public interface StatiCliniciMapper {
	StatiCliniciMapper INSTANCE = Mappers.getMapper(StatiCliniciMapper.class);
	
	StatiCliniciDto mapToDto(StatiClinici source);
	
	List<StatiCliniciDto> mapToDtoList(List<StatiClinici> source);
	
	StatiClinici mapToEntity(StatiCliniciDto source);
}
