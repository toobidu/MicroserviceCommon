package com.example.commonservice.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "SEND_MAIL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "sendMailId")
@ToString(of = {"sendMailId", "content", "mailTo", "sendMailValue", "parentSendMailId", "categorySendMailId"})
public class SendMail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "send_mail_sequence")
    @SequenceGenerator(
            name = "send_mail_sequence",
            sequenceName = "SEND_MAIL_SEQ",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long sendMailId;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "MAIL_TO", length = 500, nullable = false)
    private String mailTo;

    @Column(name = "STATUS")
    private Boolean status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    @Setter(AccessLevel.NONE)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TIME")
    @Setter(AccessLevel.NONE)
    private Date updatedTime;

    @Column(name = "CREATED_USER")
    private Long createdUser;

    @Column(name = "UPDATED_USER")
    private Long updatedUser;

    //Constructor
    public SendMail(
            Long sendMailId,
            String content,
            String mailTo,
            Boolean status,
            Long createdUser,
            Long updatedUser
    ) {
        this.sendMailId = sendMailId;
        this.content = content;
        this.mailTo = mailTo;
        this.status = status;
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
        this.createdTime = new Date();  // Set createdTime to current time
        this.updatedTime = new Date();  // Set updatedTime to current time
    }

    @PrePersist
    protected void toCreate() {
        this.createdTime = new Date();
        this.updatedTime = new Date();
    }

    @PreUpdate
    protected void toUpdate() {
        this.updatedTime = new Date();
    }
}
