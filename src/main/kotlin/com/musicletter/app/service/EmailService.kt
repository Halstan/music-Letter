package com.musicletter.app.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import javax.mail.MessagingException
import javax.mail.internet.MimeMessage

@Service
@EnableAsync
class EmailService(
    val springTemplateEngine: SpringTemplateEngine,
    val javaMailSender: JavaMailSender
    ){

    @Async
    fun sendEmail(email: SimpleMailMessage?) {
        javaMailSender.send(email)
    }

    @Async
    @Throws(MessagingException::class)
    fun sendMessageUsingThymeleafTemplate(
        to: String, subject: String, templateModel: Map<String, Any>
    ) {
        val thymeleafContext = Context()
        thymeleafContext.setVariables(templateModel)
        val htmlBody: String = this.springTemplateEngine.process("Email.html", thymeleafContext)
        sendHtmlMessage(to, subject, htmlBody)
    }

    @Throws(MessagingException::class)
    fun sendHtmlMessage(to: String, subject: String, htmlBody: String) {
        val message = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8")
        helper.setFrom("enzoarauco@gmail.com")
        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(htmlBody, true)
        javaMailSender.send(message)
    }

}