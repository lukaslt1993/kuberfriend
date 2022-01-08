package com.github.lukaslt1993.kuberfriend.client;

import com.github.lukaslt1993.kuberfriend.server.service.BookService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.github.lukaslt1993.kuberfriend.server.MatcherServiceClient;

@Route("")
public class MainView extends SplitLayout {

    private MatcherServiceClient client;
    private BookService service;

    public MainView(MatcherServiceClient client, BookService service) {
        this.client = client;
        this.service = service;

        HorizontalLayout mainContainer = new HorizontalLayout();
        mainContainer.setSpacing(true);

        TextField inputField = new TextField();
        inputField.setPlaceholder("Enter some text");
        Button submitInput = new Button("Click me");

        mainContainer.add(inputField);
        mainContainer.add(submitInput);

        VerticalLayout inputContainer = new VerticalLayout();
        inputContainer.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        inputContainer.add(mainContainer);

        VerticalLayout outputContainer = new VerticalLayout();
        outputContainer.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        this.addToPrimary(inputContainer);
        this.addToSecondary(outputContainer);

        submitInput.addClickListener(event -> addResultContainers(outputContainer, inputField.getValue()));
    }

    private void addResultContainers(VerticalLayout outputContainer, String input) {
        for (String title : service.getBookTitles()) {
            HorizontalLayout resultContainer = new HorizontalLayout();
            resultContainer.add(client.getMatch(input, title));
            outputContainer.add(resultContainer);
        }
    }

}
