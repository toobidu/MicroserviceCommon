package com.example.commonservice.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "SEND_MAIL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendMail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "send_mail_sequence")
    @SequenceGenerator(name = "send_mail_sequence", sequenceName = "SEND_MAIL_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long sendMailId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "MAIL_TO", length = 500, nullable = false)
    private String mailTo;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "CREATED_TIME")
    @CreationTimestamp
    private LocalDateTime createdTime;

    @Column(name = "UPDATED_TIME")
    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @Column(name = "CREATED_USER")
    private Long createdUser;

    @Column(name = "UPDATED_USER")
    private Long updatedUser;

    //Constructor
    public SendMail(Long sendMailId, String content, String mailTo, Boolean status, Long createdUser, Long updatedUser) {
        this.sendMailId = sendMailId;
        this.content = content;
        this.mailTo = mailTo;
        this.status = status;
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
    }
}
