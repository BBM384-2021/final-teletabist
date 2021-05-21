package com.teletabist.clubby.user.services;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.UUID;

import javax.xml.bind.ValidationException;

import com.teletabist.clubby.user.SecureUserPrincipal;
import com.teletabist.clubby.user.models.Profile;
import com.teletabist.clubby.user.models.ProfileRepository;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.models.UserDTO;
import com.teletabist.clubby.user.models.UserRepository;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository uRepository, ProfileRepository pRepository){
        this.userRepository = uRepository;
        this.profileRepository = pRepository;
    }

    public User createUser(User u){
        try{
            u.setEmail_verification_token(UUID.randomUUID().toString());
            
            //Trim and validate password format
            String _trimmed = u.getPassword().trim();
            if(_trimmed.length()>7 && _trimmed.length()<65){
                u.setPassword(passwordEncoder.encode(_trimmed));
                u = this.userRepository.save(u);
                if(u != null){
                    if(u.getProfile() == null)
                        u.setProfile(this.generateProfile(u));
                }
                return u;
            }
        }catch(DataIntegrityViolationException e){
            LoggerFactory.getLogger(UserService.class).error(e.getMessage());
        }
        return null;
    }

    public User getUser(String username){
        User u = this.userRepository.findByUsername(username);
        if(u != null){
            if(u.getProfile() == null){
                u.setProfile(this.generateProfile(u));
            }
        }
        return u;
    }

    public boolean checkPassword(User u){
        User x = this.getUser(u.getUsername());
        if(x != null){
            return this.passwordEncoder.matches(u.getPassword(), x.getPassword());
        }else{
            return false;
        }
    }

    private Profile generateProfile(User u){
        if(u != null){
            Profile p = new Profile();
            p.setUser(u);
            p.setName(u.getUsername());
            p = this.profileRepository.save(p);
            return p;
        }
        return null;
    }

    public Iterable<User> getAll(){
        Iterable<User> users = this.userRepository.findAll();
        Iterator<User> usersIterator = users.iterator();
        while(usersIterator.hasNext()){
            User _tmp = usersIterator.next();
            if(_tmp.getProfile() == null) _tmp.setProfile(this.generateProfile(_tmp));
        }
        return users;
    }

    public User updateUser(String username, User u){
        User fetched = this.getUser(username);
        if(u.getEmail() != null){
            fetched.setEmail(u.getEmail());
            fetched.setEmail_verification_token(UUID.randomUUID().toString());
            fetched.setVerified_at(null);
        }
        
        this.userRepository.save(fetched);

        return fetched;
    }

    public boolean deleteUser(String username){
        User fetched = this.getUser(username);
        if(fetched != null){
            this.profileRepository.delete(fetched.getProfile());
            this.userRepository.delete(fetched);
            return true;
        }
        return false;
    }

    public User registerUser(UserDTO userDTO) throws ValidationException{
        if(!userDTO.getPassword().equals(userDTO.getPassword_validate())){
            throw new ValidationException("Passwords doesn't match");
        }
        User u = new User();
        u.setEmail(userDTO.getEmail());
        u.setPassword(userDTO.getPassword());
        u.setUsername(userDTO.getUsername());
        u = this.createUser(u);
        Profile p = u.getProfile();
        p.setName(userDTO.getName());
        this.profileRepository.save(p);
        return u;
    }

    public boolean verifyUser(User u, String token){
        if(u != null){
            if(u.getEmail_verification_token().equals(token)){
                u.setVerified_at(new Timestamp(System.currentTimeMillis()));
                this.userRepository.save(u);
                return true;
            }
        }
        return false;
    }

    public User authUser(){
        SecureUserPrincipal pr = this.authUserPrincipal();
        if(pr != null){
            return pr.getUser();
        }
        return null;
    }

    public SecureUserPrincipal authUserPrincipal(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof SecureUserPrincipal){
            SecureUserPrincipal securePrincipal = (SecureUserPrincipal) principal;
            return securePrincipal;
        }
        return null;
    }
}