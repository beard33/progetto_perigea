package com.depa.progettinocovid.DataAggregator.mapper;
/**
 * Mapper per il passaggio da {@link com.depa.progettinocovid.models.Conteggio}
 * a {@link com.depa.progettinocovid.models.ConteggioDto}
 */


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.depa.progettinocovid.DataAggregator.models.Conteggio;
import com.depa.progettinocovid.DataAggregator.models.ConteggioDto;

@Mapper(componentModel = "spring")
public interface ConteggioMapper {

	ConteggioMapper INSTANCE = Mappers.getMapper(ConteggioMapper.class);
	
	ConteggioDto mapToDto(Conteggio source);
	
	Conteggio mapToEntity(ConteggioDto source);
	
	List<ConteggioDto> mapToDtoList(List<Conteggio> source);
}
