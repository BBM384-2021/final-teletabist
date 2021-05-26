package com.teletabist.clubby.contact.api;

import java.util.Collection;

import com.teletabist.clubby.contact.models.SystemContact;
import com.teletabist.clubby.contact.services.SystemContactService;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dev/tickets")
public class SystemContactAPIController {
    @Autowired SystemContactService systemContactService;
    @Autowired UserService userService;

    @GetMapping
    public ResponseEntity<?> index(){
        Collection<SystemContact> contacts = this.systemContactService.getAllActive();
        if(contacts != null){
            return new ResponseEntity<Collection<SystemContact>>(contacts, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Unsuccesfull", HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<?> store(@RequestBody SystemContact s){
        if(s.getUser() == null){
            User u = this.userService.authUser();
            if(u == null) return new ResponseEntity<String>("User cannot be empty!", HttpStatus.BAD_REQUEST);
        }
        SystemContact _created = this.systemContactService.createSystemContact(s);
        if(_created != null) return new ResponseEntity<SystemContact>(_created, HttpStatus.OK);
        return new ResponseEntity<String>("Unsuccesfull", HttpStatus.BAD_REQUEST);
    }
}