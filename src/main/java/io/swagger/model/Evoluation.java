package io.swagger.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Document(collection = "Evoluation")
public class Evoluation {
    private String type;
    private String name;
    //private String legendText;
    private boolean showInLegend;
    private List<DataPointWithoutX> dataPoints;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getLegendText() {
        return legendText;
    }

    public void setLegendText(String legendText) {
        this.legendText = legendText;
    }*/

    public boolean isShowInLegend() {
        return showInLegend;
    }

    public void setShowInLegend(boolean showInLegend) {
        this.showInLegend = showInLegend;
    }

    public List<DataPointWithoutX> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<DataPointWithoutX> dataPoints) {
        this.dataPoints = dataPoints;
    }
}
