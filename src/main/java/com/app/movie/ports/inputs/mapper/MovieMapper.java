package com.app.movie.ports.inputs.mapper;

import com.app.movie.domain.models.Movie;
import com.app.movie.ports.inputs.requests.MovieCreateRequest;
import com.app.movie.ports.inputs.requests.MovieUpdateRequest;
import com.app.movie.ports.inputs.responses.MovieFilterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MovieMapper {

   @Mapping(source = "idGenre", target = "genre.id")
   Movie movieCreateRequestToMovie(MovieCreateRequest aux);

   Movie movieUpdateRequestToMovie(MovieUpdateRequest aux);

   List<MovieFilterResponse> movieFilterResponseToMovieList(List<Movie> aux);
}
