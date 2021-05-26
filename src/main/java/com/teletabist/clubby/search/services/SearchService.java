package com.teletabist.clubby.search.services;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubRepository;
import com.teletabist.clubby.search.models.SearchDTO;
import com.teletabist.clubby.search.models.SearchResultDTO;
import com.teletabist.clubby.user.models.Profile;
import com.teletabist.clubby.user.models.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired ClubRepository clubRepository;
    @Autowired ProfileRepository profileRepository;

    public SearchResultDTO search(SearchDTO query){

        SearchResultDTO results = new SearchResultDTO();

        if(query.profile)
            results.users = this.profileRepository.findAll(this.searchProfileByName(query.param));
        if(query.club)
            results.clubs = this.clubRepository.findAll(this.searchClubByName(query.param));
        results.searchKey = query.param;
        return results;
    }

    private Specification<Profile> searchProfileByName(String name){
        return new Specification<Profile>(){
            private static final long serialVersionUID = -3638221117891665178L;

            @Override
            public Predicate toPredicate(
                Root<Profile> root, 
                CriteriaQuery<?> query, 
                CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"), "%"+name+"%");
            }
        };
    } 

    private Specification<Club> searchClubByName(String name){
        return new Specification<Club>(){
            private static final long serialVersionUID = -3638221117891665178L;

            @Override
            public Predicate toPredicate(
                Root<Club> root, 
                CriteriaQuery<?> query, 
                CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("name"), "%"+name+"%");
            }
        };
    } 

}