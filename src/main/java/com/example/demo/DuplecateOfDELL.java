package com.example.demo;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//(securedEnabled = true  for @Secured enable
//,jsr250Enabled = true  for @RolesAllowed enable at method, 
//prePostEnabled = true for @Pre/Post Authorize , @Pre/Post Filter enabled)
@EnableAsync
public class DuplecateOfDELL {


}
