package com.app.movie.ports.inputs.mapper;

import com.app.movie.domain.models.Personage;
import com.app.movie.ports.inputs.requests.PersonageRequest;
import com.app.movie.ports.inputs.responses.PersonageFilterResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PersonageMapper {

   Personage toModel(PersonageRequest aux);

   PersonageFilterResponse toFilter(Personage aux);

   List<PersonageFilterResponse> toListFilter(List<Personage> aux);

}
