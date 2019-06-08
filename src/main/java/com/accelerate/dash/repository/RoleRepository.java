/*
 * Version			: 1.0
 * Developer 		: Muathasim Mohamed P
 * Email			: muth4muathasim@gmail.com			
 * Date				: 06 June 2019
 * Modified Date	: 06 June 2019	
 * Comments			: 
 */


package com.accelerate.dash.repository;

import com.accelerate.dash.model.Role;
import com.accelerate.dash.model.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);
}
