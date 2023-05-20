package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.scheduler.EmailScheduler;
import com.crud.tasks.trello.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private EmailScheduler emailScheduler;
    @Autowired
    private TaskRepository taskRepository;

    public String informationEmail() {
        Context context = new Context();
        long size = taskRepository.count();
        context.setVariable("size", size);
        context.setVariable("content", emailScheduler.content(size));
        context.setVariable("taskList", taskRepository.findAll());
        context.setVariable("showButton", false);
        return templateEngine.process("email-template", context);
    }

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://jak-hry.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("goodbye_message", "Thank you!");
        context.setVariable("company_details", "${info.app.description}");
        context.setVariable("preview_message", "A new Trello card has been created.");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
