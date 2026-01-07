package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.model.dto.MemberDetailedDTO;
import be.thomasmore.bookserver.services.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@Slf4j
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Operation(summary = "get 1 member from the database.",
            description = "Member with id is fetched from database - returns detailed info. ")
    @GetMapping("{id}")
    public MemberDetailedDTO findOne(@PathVariable int id) {
        log.info(String.format("##### findOne member %d", id));
        return memberService.findOne(id);
    }

    @Operation(summary = "create a new member in the database.",
            description = "Returns new member (containing id from database). </br>" +
                    "The member number must be in the format M-XXXNN-NNN-N.")
    @PostMapping("")
    public MemberDetailedDTO create(@Valid @RequestBody MemberDetailedDTO memberDTO) {
        log.info("##### create author");
        return memberService.create(memberDTO);
    }


}
