package be.thomasmore.bookserver.services;

import be.thomasmore.bookserver.model.Award;
import be.thomasmore.bookserver.model.converters.AwardDTOConverter;
import be.thomasmore.bookserver.model.converters.AwardDetailedDTOConverter;
import be.thomasmore.bookserver.model.dto.AwardDTO;
import be.thomasmore.bookserver.model.dto.AwardDetailedDTO;
import be.thomasmore.bookserver.repositories.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AwardService {
    @Autowired
    private AwardRepository awardRepository;

    @Autowired
    private AwardDTOConverter awardDTOConverter;
    @Autowired
    private AwardDetailedDTOConverter awardDetailedDTOConverter;

    public List<AwardDTO> findAll() {
        final List<Award> awards = awardRepository.findAll();
        return awards.stream()
                .map(a -> awardDTOConverter.convertToDto(a))
                .collect(Collectors.toList());
    }

    public AwardDetailedDTO edit(int id, AwardDetailedDTO awardDto) {
        if (awardDto.getId() != id)
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("id in award (%d) does not match id in url (%d).", awardDto.getId(), id));

        Optional<Award> awardFromDb = awardRepository.findById(id);
        if (awardFromDb.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Award with id %d not found.", id));

        //overwrite fields present in serieDto - relations are not touched
        Award awardSaved = awardRepository.save(awardDetailedDTOConverter.convertToEntity(awardDto, awardFromDb.get()));
        return awardDetailedDTOConverter.convertToDto(awardSaved);
    }
}
