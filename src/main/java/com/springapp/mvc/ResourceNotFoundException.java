package com.springapp.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by cheyikung on 3/29/16.
 */

/**
 * This is the ResourceNotFoundException class
 * This class handles exception message.
 * The message should contains which id in the profile is not available in database
 *
 * @author  Che-Yi Kung, MingLu Liu, Yuebiao Ma
 * @version 1.0
 * @since 2016-03-30
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super();
    }
    public ResourceNotFoundException(String message){
        super(message);
    }

}
