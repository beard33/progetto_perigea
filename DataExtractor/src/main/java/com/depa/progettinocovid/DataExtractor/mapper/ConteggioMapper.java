package com.depa.progettinocovid.DataExtractor.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.depa.progettinocovid.DataExtractor.model.Conteggio;
import com.depa.progettinocovid.DataExtractor.model.ConteggioDto;

@Mapper(componentModel = "spring")
public interface ConteggioMapper {
	ConteggioMapper INSTANCE = Mappers.getMapper(ConteggioMapper.class);
	
	ConteggioDto mapToDto(Conteggio source);
	
	List<ConteggioDto> mapToDtoList(List<Conteggio> source);
}
