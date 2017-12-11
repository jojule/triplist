package fi.jole.triplog;

import fi.jole.triplog.data.Trip;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tag;
import com.vaadin.ui.common.HtmlImport;
import elemental.json.Json;
import elemental.json.JsonObject;

@HtmlImport("DirectionSearch.html")
@Tag("direction-search")
public class TripMap extends Component {

    public void showTrip(Trip trip) {
        if (trip == null) {
            show("", "");
        } else {
            show(trip.getStart(), trip.getEnd());
        }
    }

    private void show(String start, String end) {
        JsonObject properties = Json.createObject();
        properties.put("start", start);
        properties.put("end", end);
        getElement().callFunction("setProperties", properties);
    }
}
