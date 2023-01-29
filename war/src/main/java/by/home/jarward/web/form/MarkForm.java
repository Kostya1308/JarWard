package by.home.jarward.web.form;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MarkForm {

    private List<MarkIdForm> markIdForms;

    public List<MarkIdForm> getMarkIdForms() {
        return markIdForms;
    }

    public void setMarkIdForms(List<MarkIdForm> markIdForms) {
        this.markIdForms = markIdForms;
    }
}
