package com.crud.tasks.service;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class TrelloService {

    public static final String SUBJECT = "Tasks: New Trello Card";
    private final TrelloClient trelloClient;
    private final SimpleEmailService emailService;
    private final AdminConfig adminConfig;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card -> emailService.send(
                    Mail.builder()
                            .mailTo(adminConfig.getAdminMail())
                            .subject(SUBJECT)
                            .message("New card: " + trelloCardDto.getName() + " has been created on your Trello account")
                            .build()
//                new Mail(
//                        adminConfig.getAdminMail(),
//                        SUBJECT,
//                        "New card: " + trelloCardDto.getName() + " has been created on your Trello account"
//                )
                )
        );
        return newCard;
    }
}
