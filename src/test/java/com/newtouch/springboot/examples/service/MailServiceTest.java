package com.newtouch.springboot.examples.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by summer on 2017/5/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;
    private final String toEmail = "2197466344@qq.com";

    @Test
    public void testSimpleMail() {
        mailService.sendSimpleMail(toEmail, "test simple mail2", " hello this is simple mail....");
    }

    @Test
    public void testHtmlMail() {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3 style='color:red'>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(toEmail, "test html mail", content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath = "D:\\IdeaProjects\\WeiFuWu\\WeiFuWuDay1\\spring-boot-mail\\src\\main\\resources\\application.properties";
        mailService.sendAttachmentsMail(toEmail, "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() {
        //不能含中文
        String rscId = "aaa";
        String content = "<html><body>这是有图片的邮件：<img src='cid:" + rscId + "' ></body></html>";
        String imgPath = "D:\\Program Files\\沙箱\\业务需求2\\太享来8.3\\01-01首页（芳洲）.png";
        mailService.sendInlineResourceMail(toEmail, "主题：这是有图片的邮件", content, imgPath, rscId);
    }


    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail(toEmail, "主题：这是模板邮件", emailContent);
    }
}
