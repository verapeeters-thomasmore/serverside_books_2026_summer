package be.thomasmore.bookserver.controllers;

import be.thomasmore.bookserver.model.dto.LibraryDTO;
import be.thomasmore.bookserver.model.dto.LibraryDetailedDTO;
import be.thomasmore.bookserver.services.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libraries")
@Slf4j
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Operation(summary = "find all the libraries that are stored in the database ",
            description = "All libraries are returned. </br>" +
                    "filter on location and manager if given (ignore-case)</br>" +
                    "Otherwise all libraries are returned. </br>" +
                    "The libraries Collection contains only id and name. </br>")
    @GetMapping("")
    public Iterable<LibraryDTO> findAll(@RequestParam(required = false) String location,
                                        @RequestParam(required = false) String manager) {
        log.info("##### find libraries: %s %s", location, manager);
        return libraryService.findByFilter(location, manager);
    }

    @Operation(summary = "edit existing library in the database.",
            description = "The books are <b>not</b> updated in this library.</br>" +
                    "</br>" +
                    "Returns updated library. ")
    @PutMapping("{id}")
    public LibraryDetailedDTO edit(@PathVariable int id, @RequestBody LibraryDetailedDTO libraryDto) {
        log.info(String.format("##### edit library %d", id));
        return libraryService.edit(id, libraryDto);
    }
}
