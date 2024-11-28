package statics.app;

import statics.app.view.ViewState;

/**
 * handles all actions in the View
 *
 * @author Adrian Akipi
 *///todo comment
public class ActionHandler implements IActionHandler{
    private App app;
    private ViewState viewState;

    public ActionHandler(App pApp, ViewState pViewState) {
        this.app = pApp;
        this.viewState = pViewState;
    }
}
