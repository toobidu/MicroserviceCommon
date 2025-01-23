package com.example.commonservice.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendMailDTO {
    private Long sendMailId;
    private String content;
    private String mailTo;
    private Boolean status;
    private Long createdUser;
    private Long updatedUser;
    private Date createdTime;
    private Date updatedTime;

}
