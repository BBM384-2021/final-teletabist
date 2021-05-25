package com.teletabist.clubby.search.api;

import com.teletabist.clubby.search.models.SearchDTO;
import com.teletabist.clubby.search.models.SearchResultDTO;
import com.teletabist.clubby.search.services.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping("api/dev/search")
public class SearchAPIController {
    @Autowired SearchService searchService;

    @GetMapping("{term}")
    public ResponseEntity<?> search(
        @PathVariable String term, 
        @RequestParam(defaultValue = "true") boolean profiles, 
        @RequestParam(defaultValue = "true") boolean clubs){
        if(term.length() < 3) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        SearchDTO dto = new SearchDTO();
        dto.param = HtmlUtils.htmlEscape(term);
        dto.club = clubs;
        dto.profile = profiles;

        

        return new ResponseEntity<SearchResultDTO>(this.searchService.search(dto), HttpStatus.OK);

    }
}