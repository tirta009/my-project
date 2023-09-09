package myproject.services;

import jakarta.transaction.Transactional;
import myproject.dtos.TransactionConcertRequest;
import myproject.entities.ConcertDetail;
import myproject.entities.MstUser;
import myproject.entities.TransactionConcert;
import myproject.exception.BusinessException;
import myproject.repositories.TransactionConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class TransactionConcertService {

    @Autowired
    private TransactionConcertRepository transactionConcertRepository;

    @Autowired
    private ConcertService concertService;

    @Autowired
    private MstUserService mstUserService;

    @Transactional
    public TransactionConcert submitTransactionConcert(TransactionConcertRequest request) {

        submitValidation(request);

        ConcertDetail concertDetail = concertService.getConcertDetailById(request.getConcertDetailId());
        MstUser mstUser = mstUserService.getMstUserById(request.getUserId());

        Long oldTicketsAmount = concertDetail.getTicketsAmount();
        concertDetail.setTicketsAmount(oldTicketsAmount - request.getTicketAmount());
        concertService.saveConcertDetail(concertDetail);

        TransactionConcert transactionConcert = new TransactionConcert();
        transactionConcert.setTransactionDate(new Date());
        transactionConcert.setConcertDetail(concertDetail);
        transactionConcert.setMstUser(mstUser);
        transactionConcert.setTicketAmount(request.getTicketAmount());
        transactionConcert.setTotalPayment(request.getTotalPayment());
        transactionConcert.setStatusPayment("Not Yet Paid");
        transactionConcert.setMethodPayment(request.getMethodPayment());

        return transactionConcertRepository.save(transactionConcert);

    }

    private void submitValidation(TransactionConcertRequest request){
        if (!mstUserService.isExistMstUserById(request.getUserId())){
            throw new BusinessException("User Not Found");
        }

        if (!concertService.isExistConcertDetailById(request.getConcertDetailId())){
            throw new BusinessException("Concert Not Found");
        }

        if (request.getTicketAmount() < 0){
            throw new BusinessException("Tickets must not 0");
        }

        ConcertDetail concertDetail = concertService.getConcertDetailById(request.getConcertDetailId());
        if (concertDetail.getTicketsAmount() == 0){
            throw new BusinessException("Tickets Sold");
        }
        if (concertDetail.getTicketsAmount() - request.getTicketAmount() < 0){
            throw new BusinessException("Cannot buy " + request.getTicketAmount() + " Tickets. " +
                                        "Only "+ concertDetail.getTicketsAmount() +" tickets left");
        }

    }

    @Transactional
    public TransactionConcert cancelTransactionConcert(Long transactionConcertId){

        cancelValidation(transactionConcertId);

        TransactionConcert transactionConcert = transactionConcertRepository.getReferenceById(transactionConcertId);

        ConcertDetail concertDetail = concertService.getConcertDetailById(transactionConcert.getConcertDetail().getId());
        Long oldTicketsAmount = concertDetail.getTicketsAmount();
        concertDetail.setTicketsAmount(oldTicketsAmount + transactionConcert.getTicketAmount());
        concertService.saveConcertDetail(concertDetail);

        transactionConcert.setStatusPayment("Cancelled");

        return transactionConcertRepository.save(transactionConcert);
    }

    private void cancelValidation(Long transactionConcertId){
        if (!transactionConcertRepository.existsById(transactionConcertId)){
            throw new BusinessException("Transaction Not Found");
        }

        TransactionConcert transactionConcert = transactionConcertRepository.getReferenceById(transactionConcertId);
        if(transactionConcert.getStatusPayment().equals("Cancelled")){
            throw new BusinessException("Transaction Already Cancelled");
        }
    }

    @Transactional
    public TransactionConcert payTransactionConcert(Long transactionConcertId){

        payValidation(transactionConcertId);

        TransactionConcert transactionConcert = transactionConcertRepository.getReferenceById(transactionConcertId);
        transactionConcert.setStatusPayment("Paid");

        return transactionConcertRepository.save(transactionConcert);
    }

    private void payValidation(Long transactionConcertId){
        if (!transactionConcertRepository.existsById(transactionConcertId)){
            throw new BusinessException("Transaction Not Found");
        }

        TransactionConcert transactionConcert = transactionConcertRepository.getReferenceById(transactionConcertId);
        if(transactionConcert.getStatusPayment().equals("Cancelled")){
            throw new BusinessException("Transaction Already Cancelled");
        }

        if(transactionConcert.getStatusPayment().equals("Paid")){
            throw new BusinessException("Transaction Already Paid");
        }
    }

}
