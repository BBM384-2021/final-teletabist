package com.teletabist.clubby.contact.services;

import java.util.Collection;

import com.teletabist.clubby.contact.core.SystemContactPriority;
import com.teletabist.clubby.contact.core.SystemContactStatus;
import com.teletabist.clubby.contact.core.SystemContactTypes;
import com.teletabist.clubby.contact.models.SystemContact;
import com.teletabist.clubby.contact.models.SystemContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemContactService {
    private SystemContactRepository systemContactRepository;

    @Autowired 
    public SystemContactService(SystemContactRepository systemContactRepository){
        this.systemContactRepository = systemContactRepository;
    }

    public Collection<SystemContact> getAllActive(){
        Collection<SystemContact> syscontact = this.systemContactRepository.findByStatus(SystemContactStatus.OPEN);
        syscontact.addAll(this.systemContactRepository.findByStatus(SystemContactStatus.PROCESSING));
        return syscontact;
    }

    public SystemContact createSystemContact(SystemContact s){
        if(s.getMessage() == null) return null;  
        if(s.getMessage().length()<1) return null;
        if(s.getUser() == null) return null;
        if(s.getContactType() == null) s.setContactType(SystemContactTypes.SYSTEM);
        if(s.getStatus() == null) s.setStatus(SystemContactStatus.OPEN);
        if(s.getStatus() == SystemContactStatus.CLOSED) return null;
        if(s.getPriority() == null) s.setPriority(SystemContactPriority.LOW);
        s = this.systemContactRepository.save(s);
        return s;
    }

    public SystemContact updateSystemContact(int id, SystemContact s){
        SystemContact _persist = this.systemContactRepository.getOne(id);
        if(_persist == null) return null;
        if(_persist.getStatus() == SystemContactStatus.CLOSED) return null;
        if(s.getMessage() != null){
            if(s.getMessage().length()>0) _persist.setMessage(s.getMessage());
        }
        if(s.getPriority() != null) _persist.setPriority(s.getPriority());
        s = this.systemContactRepository.save(s);
        return s;
    }
}