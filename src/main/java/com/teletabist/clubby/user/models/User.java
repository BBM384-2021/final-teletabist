package com.teletabist.clubby.user.models;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.teletabist.clubby.club.models.UsersClubInterest;

import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * User model holds basic user information.
 * The model is moslty used to provide authentication
 * and relations of users with other models.
 * 
 * @author Yigit Koc, Teletabist
 * @version 1.0.0
 * @since 1.0.0
 * @category Model
 */
@Entity
@Table(name="users")
public class User {
    /**
     * Identifaction number is unique to user and
     * defined by database solution.
     * {Getter}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Integer id;

    /**
     * Username must be a unique, alphanumeric string 
     * subset of ^[a-zA-Z][a-zA-Z0-9-_]{0,}[a-zA-Z0-9]$ 
     * with maximum of 32 chars.
     * {Getter, Setter}
     */
    @Column(unique = true, nullable = false, length = 32)
    private String username;

    /**
     * Email must meet RFC5322 Official Standard, must be unique
     * @see http://emailregex.com/
     * {Getter, Setter}
     */
    @Column(unique = true, nullable = false, length = 255)
    private String email;

    /**
     * Password must be encrypted for obious reasons.
     * Keep in mind that encryption must be done
     * asymmetrically and validated via comparison of
     * encryption. Suggested algorithms are BYCRPYT and 
     * SHA256. Example implementation is provided. User
     * password is at most 64 chars, 8 at least.
     * @see https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
     * {Getter, Validate, Setter}
     */
    @Column(nullable = false, length = 255)
    private String password;

    /**
     * Password reset token allows users to change password
     * without authorization over login. Since this method
     * is not secure for authorization, it is only valid 
     * until password_reset_end.
     * {Getter, Validate, Setter}
     */
    @JsonIgnore
    @Column(length = 255)
    private String password_reset_token = "";

    /**
     * Password reset end provides time security for reset
     * token.
     * {Getter, Setter}
     */
    @JsonIgnore
    private Timestamp password_reset_end = null;

    /**
     * SELF_EXPLANATORY
     * {Getter, Setter}
     */
    @JsonIgnore
    @Column(nullable = false, length = 255)
    private String email_verification_token;

    /**
     * Date of verification of email.
     * {Getter, Setter}
     */
    @JsonIgnore
    private Timestamp verified_at = null;

    /**
     * Date user account is created
     * {Getter, Setter}
     */
    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp created_at;

    /**
     * Last time an entity is updated.
     * {Getter, Setter}
     */
    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updated_at;

    @JsonManagedReference
    @OneToMany(mappedBy = "id", fetch=FetchType.EAGER)
    private List<UserRole> roles;

    @JsonManagedReference
    @OneToOne(mappedBy = "user", fetch=FetchType.EAGER)
    private Profile profile;

    /**
     * @return true if the user is validated, false otherwise.
     */
    public boolean isVerified(){
        if(this.verified_at != null){
            return this.verified_at.before(new Date());
        }
        return false;
    }

    // Getters and Setters
    /**
     * @return Id of the user
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @return Username of the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Set the username for the user. Doesn't check uniqueness on 
     * database since this consumes unnecessary resource.
     * @param username A username, alphanumeric string subset of ^[a-zA-Z][a-zA-Z0-9-_]{0,30}[a-zA-Z0-9]$ with maximum of 32 chars.
     * @throws InvalidParameterException
     */
    public void setUsername(String username) throws InvalidParameterException{
        String _trimmed = username.trim();
        _trimmed = _trimmed.toLowerCase();
        if(User.ValidateUsername(_trimmed)){
            this.username = _trimmed;
        }else{
            throw new InvalidParameterException("Username is not valid!");
        }
    }

    /**
     * @return Email of the user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set email of the user
     * @param email A valid RFC5322 email address.
     */
    public void setEmail(String email) {
        String _trimmed = email.trim();
        if(User.MAIL_VALIDATOR.isValid(_trimmed)){
            this.email = email;
        }else{
            throw new InvalidParameterException("Email is not valid!");
        }
    }

    /**
     * @return Hashed password of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Set password from explicit string.
     * This function doesn't has password internally.
     * @param password hashed password
     */
    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword_reset_token() {
        return password_reset_token;
    }

    public void setPassword_reset_token(String password_reset_token) {
        this.password_reset_token = password_reset_token;
    }

    public Timestamp getPassword_reset_end() {
        return password_reset_end;
    }

    public void setPassword_reset_end(Timestamp password_reset_end) {
        this.password_reset_end = password_reset_end;
    }

    public String getEmail_verification_token() {
        return email_verification_token;
    }

    public void setEmail_verification_token(String email_verification_token) {
        this.email_verification_token = email_verification_token;
    }

    public Timestamp getVerified_at() {
        return verified_at;
    }

    public void setVerified_at(Timestamp verified_at) {
        this.verified_at = verified_at;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    //Statics
    
    /**
     * Static username regex pattern used in username validations.
     */
    private static final Pattern VALID_USERNAME_PATTERN = 
    Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_]{0,30}[a-zA-Z0-9]$", Pattern.CASE_INSENSITIVE);

    /**
     * Static email validator instance
     */
    private static final EmailValidator MAIL_VALIDATOR = 
    new EmailValidator(false, false, DomainValidator.getInstance(false));

    /**
     * Static username validator.
     * @return True if username is valid in system standards, false otherwise
     */
    public static boolean ValidateUsername(String usernameToValidate){
        if(usernameToValidate.length()<32){
            if(User.VALID_USERNAME_PATTERN.matcher(usernameToValidate).find()){
                return true;
            }
        }
        return false;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}