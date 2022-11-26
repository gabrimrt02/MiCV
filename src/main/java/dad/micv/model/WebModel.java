package dad.micv.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WebModel {
    
    private StringProperty url = new SimpleStringProperty();

    public StringProperty urlProperty() {
        return url;
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String newUrl) {
        url.set(newUrl);
    }

}
