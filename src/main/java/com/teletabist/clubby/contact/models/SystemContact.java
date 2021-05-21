package com.teletabist.clubby.contact.models;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.contact.core.SystemContactPriority;
import com.teletabist.clubby.contact.core.SystemContactStatus;
import com.teletabist.clubby.contact.core.SystemContactTypes;
import com.teletabist.clubby.user.models.User;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "SYSTEM_CONTACTS")
public class SystemContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private int id;

    @JsonIgnoreProperties({"profile","roles", "created_at", "updated_at"})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public User getUser(){ return this.user; }
    public void setUser(User u){ this.user = u; }

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    public String getMessage(){ return this.message; }
    public void setMessage(String message) { this.message = message.trim(); }

    @Enumerated(EnumType.ORDINAL)
    @Column(length = 10, nullable = false)
    private SystemContactPriority priority;

    public SystemContactPriority getPriority() { return priority; }
    public void setPriority(SystemContactPriority priority) { this.priority = priority; }

    @Enumerated(EnumType.ORDINAL)
    @Column(length = 10, nullable = false)
    private SystemContactStatus status;

    public SystemContactStatus getStatus() { return status; }
    public void setStatus(SystemContactStatus status) { this.status = status; }

    @Basic
    @Column(name = "type", length = 10, nullable = false)
    private int type;

    @Transient
    private SystemContactTypes contactType;

    public SystemContactTypes getContactType() { return contactType; }
    public void setContactType(SystemContactTypes contactType) { this.contactType = contactType; this.fillPersist();}
    
    @PostLoad
    public void fillTransient(){
        if(this.type > 0){
            this.contactType = SystemContactTypes.valueOf(this.type);
        }else{
            this.contactType = SystemContactTypes.INVALID;
        }
    }

    @PrePersist
    public void fillPersist(){
        if(this.contactType != null){
            this.type = contactType.type;
        }
    }

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp created_at;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updated_at;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "id", nullable = true)
    private Club club;

}