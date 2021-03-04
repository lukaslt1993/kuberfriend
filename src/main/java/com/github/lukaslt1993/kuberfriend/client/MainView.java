package com.github.lukaslt1993.kuberfriend.client;

import com.github.lukaslt1993.kuberfriend.Application;
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

    public MainView(MatcherServiceClient client) {
        this.client = client;
        HorizontalLayout horLay = new HorizontalLayout();
        horLay.setSpacing(true);
        TextField text = new TextField();
        text.setPlaceholder("Enter some text");
        Button button = new Button("Click me");
        horLay.add(text);
        horLay.add(button);
        VerticalLayout verLay = new VerticalLayout();
        verLay.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        verLay.add(horLay);
        VerticalLayout verLay2 = new VerticalLayout();
        verLay2.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        this.addToPrimary(verLay);
        this.addToSecondary(verLay2);
        button.addClickListener(event -> addResultContainers(verLay2, text.getValue()));
    }

    private void addResultContainers(VerticalLayout vl, String input) {
        for (String title : Application.getBookTitles()) {
            HorizontalLayout hl = new HorizontalLayout();
            hl.add(client.getMatch(input, title));
            vl.add(hl);
        }
    }

}
