package com.teletabist.clubby.event.core;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile; //Do we need this? Given in some sources, not sure what this is for... -Eren K.
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/*  Might need to create 3 different scripts (classes) for the 3 methods given in the below class -> ZoomMeetingService (class)
    1) createMeeting()
    2) listMeetings()
    3) getZoomMeetingById()
    also might need to add the generateZoomJWTToken() method to each one as they all use them.
        -Eren K.
*/
public class ZoomMeetingService {

    public ZoomMeetingObjectDTO createMeeting(ZoomMeetingObjectDTO zoomMeetingObjectDTO) {
        //log.debug("Request to create a Zoom meeting");

        // replace zoomUserId with your user ID
        String apiUrl = "https://api.zoom.us/v2/users/" + zoomUserId + "/meetings";

        // replace with your password or method
        zoomMeetingObjectDTO.setPassword(yourPass);
        // replace email with your email
        zoomMeetingObjectDTO.setHost_email("yourEmail");

        // Optional Settings for host and participant related options
        ZoomMeetingSettingsDTO settingsDTO = new ZoomMeetingSettingsDTO();
        settingsDTO.setJoin_before_host(false);
        settingsDTO.setParticipant_video(true);
        settingsDTO.setHost_video(false);
        settingsDTO.setAuto_recording("cloud");
        settingsDTO.setMute_upon_entry(true);
        zoomMeetingObjectDTO.setSettings(settingsDTO);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + generateZoomJWTToken());
        headers.add("content-type", "application/json");
        HttpEntity<ZoomMeetingObjectDTO> httpEntity = new HttpEntity<ZoomMeetingObjectDTO>(zoomMeetingObjectDTO, headers);
        ResponseEntity<ZoomMeetingObjectDTO> zEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, ZoomMeetingObjectDTO.class);
        if(zEntity.getStatusCodeValue() == 201) {
            //log.debug("Zooom meeeting response {}",zEntity);
            return zEntity.getBody();
        } else {
            //log.debug("Error while creating zoom meeting {}", zEntity.getStatusCode());
        }
        return zoomMeetingObjectDTO;
    }

    /**
     * Request to list all meetings by userId/email of the user
     *
     * @param userIdOrEmail optional userId/email value
     *
     * @param meetingType scheduled/live/upcoming
     *
     * @return zoomMeetingsListResponseDTO the dto containing list of meetings
     */
    //@Override (Override what? Couldn't find in the sources but given with the Override, not sure why... -Eren K.)
    public ZoomMeetingsListResponseDTO listMeetings(Optional<String> userIdOrEmail, Optional<String> meetingType) {
        //log.debug("Request to list all Zoom meetings by User id or email {}", userIdOrEmail);

        // replace "me" with user id in case, listing meetings for a different user than admin
        String listMeetingUrl = "https://api.zoom.us/v2/users/me/meetings";
        // replace either user Id or email here with your user id/email
        if(userIdOrEmail.isPresent()) {
            listMeetingUrl = "https://api.zoom.us/v2/users/"+ userIdOrEmail.get() +"/meetings";
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + generateZoomJWTToken());
        headers.add("content-type", "application/json");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(listMeetingUrl);
        if(meetingType.isPresent()) {
            urlBuilder.queryParam("type", meetingType.get());
        }
        ResponseEntity<ZoomMeetingsListResponseDTO> response = restTemplate
                .exchange(urlBuilder.toUriString(), HttpMethod.GET, requestEntity, ZoomMeetingsListResponseDTO.class);
        if(response.getStatusCodeValue() == 200) {
            return response.getBody();
        } else if (response.getStatusCodeValue() == 404) {
            throw new InternalServerException("User id or email not found for supplied value");
        }
        return null; //return "response" in line#86? Not sure what to return... -Eren K.
    }

    /**
     * Get ZoomMeeting by Meeting id
     *
     * @param meetingId the id of meeting from Zoom
     * @return the meetingObjectDTO with meeting details
     */
    //@Override (Override what? Couldn't find in the sources but given with the Override, not sure why... -Eren K.)
    public ZoomMeetingObjectDTO getZoomMeetingById(String meetingId) {
        //log.debug("Request to get single meeting by id {}", meetingId);

        String getMeetingUrl = "https://api.zoom.us/v2/meetings/" + meetingId;
        //log.debug("Meeting Url {}",getMeetingUrl);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + generateZoomJWTToken());
        headers.add("content-type", "application/json");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<ZoomMeetingObjectDTO> zoomEntityRes =  restTemplate
                .exchange(getMeetingUrl, HttpMethod.GET, requestEntity, ZoomMeetingObjectDTO.class);
        if(zoomEntityRes.getStatusCodeValue() == 200) {
            return zoomEntityRes.getBody();
        } else if (zoomEntityRes.getStatusCodeValue() == 404) {
            throw new InternalServerException("User id or email not found for supplied value");
        }
        return null; //return zoomEntityRes in line#113? Not sure what to return... -Eren K.
    }

    /**
     * Generate JWT token for Zoom using api credentials
     *
     * @return JWT Token String
     */
    private String generateZoomJWTToken() {
        String id = UUID.randomUUID().toString().replace("-", "");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Date creation = new Date(System.currentTimeMillis());
        Date tokenExpiry = new Date(System.currentTimeMillis() + (1000 * 60));

        Key key = Keys
                .hmacShaKeyFor(zoomApiSecret.getBytes());
        return Jwts.builder()
                .setId(id)
                .setIssuer(zoomApiKey)
                .setIssuedAt(creation)
                .setSubject("")
                .setExpiration(tokenExpiry)
                .signWith(key, signatureAlgorithm)
                .compact();
    }
}
