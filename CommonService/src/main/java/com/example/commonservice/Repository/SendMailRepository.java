package com.example.commonservice.Repository;

import com.example.commonservice.Model.Entity.SendMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SendMailRepository extends JpaRepository<SendMail, Long> {

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
            @Param("sendMailId") Long sendMailId,
            @Param("content") String content,
            @Param("mailTo") String mailTo,
            @Param("status") Boolean status,
            @Param("createdTime") LocalDateTime createdTime,
            @Param("updatedTime") LocalDateTime updatedTime,
            @Param("createdUser") Long createdUser,
            @Param("updatedUser") Long updatedUser);
}
