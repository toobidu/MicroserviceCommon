package com.example.commonservice.Model.DTO;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SendMailDTO {
    Long sendMailId;
    String content;

    @Size(max = 500)
    String mailTo;
    Boolean status;
    LocalDateTime createdTime;
    LocalDateTime updatedTime;
    Long createdUser;
    Long updatedUser;

}
