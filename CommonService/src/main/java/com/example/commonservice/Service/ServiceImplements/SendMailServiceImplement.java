package com.example.commonservice.Service.ServiceImplements;

import com.example.commonservice.Model.Entity.SendMail;
import com.example.commonservice.Repository.SendMailRepository;
import com.example.commonservice.Service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SendMailServiceImplement implements SendMailService {

    private final SendMailRepository sendMailRepository;

    @Autowired
    public SendMailServiceImplement(SendMailRepository sendMailRepository) {
        this.sendMailRepository = sendMailRepository;
    }

    //Get all mails

    @Override
    public List<SendMail> getAllSendMail() {
        return sendMailRepository.findAll();
    }

    //Create mail
    @Override
    @Transactional(readOnly = false)
    public SendMail createSendMail(SendMail sendMail) {
        try {
            // Ensure ID is null when creating new
            sendMail.setSendMailId(null);

            // Save entity
            return sendMailRepository.save(sendMail);
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException("Send mail code already exists", e);

        } catch (Exception e) {
            throw new RuntimeException("Error creating mail", e);
        }
    }

    //Update mail
    @Override
    @Transactional(readOnly = false)
    public Optional<SendMail> updateSendMail(Long sendMailId, SendMail sendMail) {
        try {
            return sendMailRepository.findById(sendMailId)
                    .map(existingSendMail -> {
                        // Update each field instead of saving directly
                        existingSendMail.setContent(sendMail.getContent());
                        existingSendMail.setMailTo(sendMail.getMailTo());
                        existingSendMail.setStatus(sendMail.getStatus());
                        existingSendMail.setCreatedUser(sendMail.getCreatedUser());
                        existingSendMail.setUpdatedUser(sendMail.getUpdatedUser());
                        return sendMailRepository.save(existingSendMail);
                    });
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Send mail code already exists", e);
        } catch (Exception e) {
            throw new RuntimeException("Error updating send mail", e);
        }
    }

    //Delete mail
    @Override
    @Transactional(readOnly = false)
    public boolean deleteSendMail(Long sendMailId) {
        try {
            if (sendMailRepository.existsById(sendMailId)) {
                sendMailRepository.deleteById(sendMailId);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting send mail", e);
        }
    }

    @Override
    public List<SendMail> searchSendMails(Long sendMailId, String content, String mailTo, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser) {
        return sendMailRepository.searchSendMailsBy(sendMailId, content, mailTo, status, createdTime, updatedTime, createdUser, updatedUser);
    }
}
