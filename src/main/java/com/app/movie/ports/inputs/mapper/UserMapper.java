package com.app.movie.ports.inputs.mapper;

import com.app.movie.domain.models.User;
import com.app.movie.ports.inputs.requests.UserRequestRegistration;
import com.app.movie.ports.inputs.responses.UserDetailsResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

   User userRegistrationToUser(UserRequestRegistration aux);

   UserDetailsResponse userToUserDetails(User aux);
}
