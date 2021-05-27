package com.teletabist.clubby.club.services;


import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubFormDTO;
import com.teletabist.clubby.club.models.ClubRepository;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class ClubService {
    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    //TODO: add try/catch block and verification parts
    public Club addClub(Club club) {
        if (club.getSlug() == null) {

            club.setSlug(createSlug(club));

        } else {
            Club checkClub = clubRepository.findDistinctBySlug(club.getSlug());
            
            if (checkClub != null) {
               club.setSlug(this.createSlug(club));
            }
        }
        club = clubRepository.save(club);
        return club;
    }

    private String createSlug(Club club) {

        String slugInMaking = club.getName().toLowerCase().replaceAll(" ", "-");

        if (slugInMaking.length() > 144) {
            slugInMaking = slugInMaking.substring(0, 135);
        }

        Club checkingClub = clubRepository.findDistinctBySlug(slugInMaking);
        String slugInChange = slugInMaking;

        for (int i = 0; i < 10 && checkingClub != null; i++) {
            Random random = new Random();
            slugInChange = slugInMaking + random.nextInt(1000000000);
            checkingClub = clubRepository.findDistinctBySlug(slugInChange);
        }

        return slugInChange;
    }

    public Club getClub(String slug) {
        return clubRepository.findDistinctBySlug(slug);
    }

    public Iterable<Club> getAll() {
        return clubRepository.findAll();
    }

    public Club updateEntireClub(Club club, String slug) {
        Club updatedClub = clubRepository.findDistinctBySlug(slug);

        if (updatedClub == null) {
            try {
                throw new NotFoundException("There isn't a club with that slug");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        
        if (club.getSlug() == null) {
            updatedClub.setSlug(updatedClub.getSlug());
        } else {
            Club updatingClub = clubRepository.findDistinctBySlug(club.getSlug());

            if (updatingClub == null) {
                updatedClub.setSlug(club.getSlug());
            } else if (updatingClub.getId() != updatedClub.getId()) {
                throw new InvalidParameterException("Cannot be updated to this slug. This slug already exists");
            } else {
                updatedClub.setSlug(updatedClub.getSlug());
            }
        }

        updatedClub.setName((club.getName() != null)?club.getName():updatedClub.getName());
        updatedClub.setDescription((club.getDescription() != null)?club.getDescription():updatedClub.getDescription());
        updatedClub.setProfile_photo_url((club.getProfile_photo_url() != null)?club.getProfile_photo_url():updatedClub.getProfile_photo_url());
        updatedClub.setWebsite((club.getWebsite() != null)?club.getWebsite():updatedClub.getWebsite());
        updatedClub.setLocation((club.getLocation() != null)?club.getLocation():updatedClub.getLocation());

        clubRepository.save(updatedClub);
        return updatedClub;
    }

    @Transactional
    public Boolean deleteClub(String slug) {
        if (clubRepository.deleteBySlug(slug) > 0) return true;
        return false;
    }

    public Club createClub(ClubFormDTO clubFormDTO) {
        Club club = new Club();
        club.setName(clubFormDTO.getName());
        club.setSlug(clubFormDTO.getSlug());
        club.setDescription(clubFormDTO.getDescription());
        club.setLocation(clubFormDTO.getLocation());
        Club parent = null;
        if(clubFormDTO.getParent_id() > -1){
            Optional<Club> pdb = this.clubRepository.findById(clubFormDTO.getParent_id());
            if(pdb.isPresent()){
                parent = pdb.get();
            }
        }
        club.setParent(parent);
        club.setWebsite(clubFormDTO.getWebsite());
        return addClub(club);
    }

    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    private static int paginationLimit = 10;

    public Collection<Club> listClubs(int page){
        int pageCount = this.pageCount();
        if(page<0){
            page = 0;
        }else if(page>=pageCount){
            page=pageCount-1;
        }
      
        return clubRepository.findAll(this.excludeSubclubs(),PageRequest.of(page, paginationLimit)).toList();
    }

    public Collection<Club> listClubs(){
        return clubRepository.findAll(this.excludeSubclubs());
    }

    private Specification<Club> excludeSubclubs(){
        return new Specification<Club>(){
			private static final long serialVersionUID = 1L;

			@Override
            public Predicate toPredicate(Root<Club> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return root.get("parent").isNull();
            }
        };

    }

    public int pageCount(){
        long count = clubRepository.count(this.excludeSubclubs());
        int pageCount = (int) Math.round(Math.ceil(count/(double) paginationLimit));
        return pageCount;
    }

    /*public Iterable<UserRole> getMembers(Integer club_id) {
        Iterable<ClubRoles> cr;
        cr = clubRolesRepository.findByClub_id(club_id);
        for (ClubRoles clubRoles : cr) {
            userRoleRepository.findById(clubRoles)
        }
        return ;
    }*/
}

