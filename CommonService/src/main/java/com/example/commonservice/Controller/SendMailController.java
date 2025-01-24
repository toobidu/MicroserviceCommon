package com.example.commonservice.Controller;

import com.example.commonservice.Model.Entity.SendMail;
import com.example.commonservice.Service.SendMailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/common/front-end/send-mail")
public class SendMailController {

    private final SendMailService sendMailService;

    @Autowired
    public SendMailController(SendMailService sendMailService) {
        this.sendMailService = sendMailService;
    }

    //Get all send mails
    @GetMapping
    public ResponseEntity<List<SendMail>> getAllSendMail() {
        List<SendMail> sendMails = sendMailService.getAllSendMail();
        return ResponseEntity.ok(sendMails);
    }

    //Create a new send mail
    @PostMapping
    public ResponseEntity<SendMail> createSendMail(@RequestBody @Valid SendMail sendMail) {
        SendMail createdSendMail = sendMailService.createSendMail(sendMail);
        return ResponseEntity.ok(createdSendMail);
    }

    //Update a send mail
    @PutMapping("/{id}")
    public ResponseEntity<SendMail> updateSendMail(@PathVariable Long id, @RequestBody @Valid SendMail sendMail) {
        Optional<SendMail> updatedSendMail = sendMailService.updateSendMail(id, sendMail);
        return updatedSendMail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete a send mail
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSendMail(@PathVariable Long id) {
        boolean isDeleted = sendMailService.deleteSendMail(id);
        if (isDeleted) {
            return ResponseEntity.ok("Send mail is deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error!");
        }
    }

    //Search send mails by multiple criteria
    @GetMapping("/search")
    public ResponseEntity<List<SendMail>> searchSendMails(
            @RequestParam(required = false) Long sendMailId,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String mailTo,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date updatedTime,
            @RequestParam(required = false) Long createdUser,
            @RequestParam(required = false) Long updatedUser) {

        List<SendMail> sendMails = sendMailService.searchSendMails(sendMailId, content, mailTo, status, createdTime, updatedTime, createdUser, updatedUser);
        return ResponseEntity.ok(sendMails);
    }
}
