package myproject.services;

import jakarta.transaction.Transactional;
import myproject.entities.MstUser;
import myproject.repositories.MstUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MstUserService {

    @Autowired
    private MstUserRepository mstUserRepository;

    @Transactional
    public MstUser getMstUserById(Long id){
        return mstUserRepository.getReferenceById(id);
    }

    @Transactional
    public boolean isExistMstUserById(Long id){
        return mstUserRepository.existsById(id);
    }


}
