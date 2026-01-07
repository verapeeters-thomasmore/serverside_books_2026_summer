package be.thomasmore.bookserver.services;


import be.thomasmore.bookserver.model.Member;
import be.thomasmore.bookserver.model.converters.MemberDetailedDTOConverter;
import be.thomasmore.bookserver.model.dto.MemberDetailedDTO;
import be.thomasmore.bookserver.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberDetailedDTOConverter memberDTOConverter;


    public MemberDetailedDTO findOne(int id) {
        final Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty())
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Member with id %d does not exist.", id));
        return memberDTOConverter.convertToDto(member.get());
    }

    public boolean checkMemberNumber(MemberDetailedDTO memberDTO) {
        final String memberNumber = memberDTO.getMemberNumber();
        final String city = memberDTO.getCity();

        if (!memberNumber.matches("M-...\\d\\d-\\d\\d\\d-\\d")) return false;
        if (!memberNumber.substring(2, 5).equals(city.substring(0, 3).toUpperCase())) return false;

        String numbersForChecksum = memberNumber.substring(5,11).replaceAll("-", "");
        int sum = 0;
        for (int i = 0; i < numbersForChecksum.length(); i++) {
            sum += numbersForChecksum.charAt(i) - '0';
        }
        int calculatedChecksum = sum % 9;
        int expectedChecksum = memberNumber.charAt(memberNumber.length() - 1) - '0';
        if (calculatedChecksum != expectedChecksum) return false;

        return true;
    }

    public MemberDetailedDTO create(MemberDetailedDTO memberDTO) {
        if (!checkMemberNumber(memberDTO))
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "MemberNumber does not match the format");

        final Member member = memberDTOConverter.convertToEntity(memberDTO);
        return memberDTOConverter.convertToDto(memberRepository.save(member));
    }
}
