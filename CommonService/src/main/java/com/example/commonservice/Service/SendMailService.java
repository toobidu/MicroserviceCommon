package com.example.commonservice.Service;

import com.example.commonservice.Model.Entity.SendMail;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SendMailService {

    //Get all
    List<SendMail> getAllSendMail();

    //Create
    SendMail createSendMail(SendMail sendMail);

    //Update
    Optional<SendMail> updateSendMail(Long sendMailId, SendMail sendMail);

    //Delete
    boolean deleteSendMail(Long sendMailId);

    //Search
    List<SendMail> searchSendMails(Long sendMailId, String content, String mailTo, Boolean status, Date createdTime, Date updatedTime, Long createdUser, Long updatedUser);
}
