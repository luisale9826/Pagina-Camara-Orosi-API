package com.camaraturismoorosi.apicamaraturismoorosi.auth;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
    
}
