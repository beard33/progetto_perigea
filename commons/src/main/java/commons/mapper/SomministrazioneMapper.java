package commons.mapper;
/**
 * Mapper per il passaggio da {@link com.depa.progettinocovid.models.Conteggio}
 * a {@link com.depa.progettinocovid.models.ConteggioDto}
 */


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import commons.model.Somministrazione;
import commons.model.SomministrazioneDto;

@Mapper(componentModel = "spring")
public interface SomministrazioneMapper {

	SomministrazioneMapper INSTANCE = Mappers.getMapper(SomministrazioneMapper.class);
	
	SomministrazioneDto mapToDto(Somministrazione source);
	
	Somministrazione mapToEntity(SomministrazioneDto source);
	
	List<SomministrazioneDto> mapToDtoList(List<Somministrazione> source);
}
