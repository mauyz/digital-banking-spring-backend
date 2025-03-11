package org.sid.ebankingbackend.mappers;

import org.sid.ebankingbackend.dtos.AppUserDto;
import org.sid.ebankingbackend.security.entities.AppUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AppUserMapperImpl {
	
	public AppUserDto fromAppUser(AppUser user) {
		AppUserDto appUserDto = new AppUserDto();
		BeanUtils.copyProperties(user, appUserDto);
		return appUserDto;	
	}

}
