package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.model.dto.AwardDTO;
import be.thomasmore.bookserver.model.dto.AwardDetailedDTO;
import be.thomasmore.bookserver.services.AwardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/awards")
@Slf4j
public class AwardController {
    @Autowired
    private AwardService awardService;

    @Operation(summary = "find all the awards that are stored in the database ",
            description = "The awards Collection contains only id and awardName.</br>" +
                    "All awards are returned.</br>")
    @GetMapping("")
    public Iterable<AwardDTO> findAll() {
        log.info("##### findAll awards");
        return awardService.findAll();
    }

    @Operation(summary = "edit existing award in the database.",
            description = "Returns updated award.")
    @PutMapping("{id}")
    public AwardDetailedDTO edit(@PathVariable int id, @RequestBody AwardDetailedDTO awardDto) {
        log.info(String.format("##### edit award %d", id));
        return awardService.edit(id, awardDto);
    }
}
