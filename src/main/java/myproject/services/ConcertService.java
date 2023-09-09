package myproject.services;

import jakarta.transaction.Transactional;
import myproject.dtos.ConcertHeaderResponse;
import myproject.entities.ConcertDetail;
import myproject.entities.ConcertHeader;
import myproject.repositories.ConcertDetailRepository;
import myproject.repositories.ConcertHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ConcertService {

    @Autowired
    private ConcertHeaderRepository concertHeaderRepository;

    @Autowired
    private ConcertDetailRepository concertDetailRepository;

    @Transactional
    public ConcertDetail getConcertDetailById(Long id){
        return concertDetailRepository.getReferenceById(id);
    }

    @Transactional
    public boolean isExistConcertDetailById(Long id){
        return concertDetailRepository.existsById(id);
    }

    @Transactional
    public ConcertDetail saveConcertDetail(ConcertDetail entity){
        return concertDetailRepository.save(entity);
    }

    @Transactional
    public Page<ConcertHeaderResponse> findConcert(String concertName, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<ConcertHeader> concertHeaders =
                concertHeaderRepository.findConcert(concertName,
                                                    pageable);

        List<ConcertHeaderResponse> concertHeaderResponses =
                concertHeaders.getContent().stream().map(
                    ConcertHeaderResponse::entityToDto
                ).toList();

        return new PageImpl<>(concertHeaderResponses, pageable, concertHeaders.getTotalElements());
    }

}
