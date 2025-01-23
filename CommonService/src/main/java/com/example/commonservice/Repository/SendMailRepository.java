package com.example.commonservice.Repository;

import com.example.commonservice.Model.Entity.SendMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SendMailRepository extends JpaRepository<SendMail, Long>{

    //Custom query method for searching send mails by multiple criteria
    @Query("SELECT sm FROM SendMail sm WHERE "
            + "(:sendMailId IS NULL OR sm.sendMailId = :sendMailId) AND "
            + "(:content IS NULL OR sm.content = :content) AND "
            + "(:mailTo IS NULL OR sm.mailTo = :mailTo) AND "
            + "(:status IS NULL OR sm.status = :status) AND "
            + "(:createdTime IS NULL OR sm.createdTime = :createdTime) AND "
            + "(:updatedTime IS NULL OR sm.updatedTime = :updatedTime) AND "
            + "(:createdUser IS NULL OR sm.createdUser = :createdUser) AND "
            + "(:updatedUser IS NULL OR sm.updatedUser = :updatedUser)")
    List<SendMail> searchSendMailsBy(
            Long sendMailId,
            String content,
            String mailTo,
            Boolean status,
            Date createdTime,
            Date updatedTime,
            Long createdUser,
            Long updatedUser);
}
