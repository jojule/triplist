package fi.jole.triplog;

import com.vaadin.data.provider.SortDirection;
import com.vaadin.router.Route;
import fi.jole.triplog.data.Trip;
import fi.jole.triplog.data.TripRepository;
import fi.jole.triplog.workaround.SpringDataProviderBuilder;
import com.vaadin.ui.common.HasStyle;
import com.vaadin.ui.common.StyleSheet;
import com.vaadin.ui.grid.Grid;
import com.vaadin.ui.html.Div;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@StyleSheet("styles.css")
@Route(value = "")
public class TripList extends Div implements HasStyle {

    @Autowired
    private TripRepository repository;

    private Grid<Trip> grid = new Grid<>();
    private TripMap map = new TripMap();

    @PostConstruct
    public void init() {
        setClassName("trip-list");

        grid.addColumn(Trip::getFormattedDate).setHeader("Date");
        grid.addColumn(Trip::getStart).setHeader("From");
        grid.addColumn(Trip::getEnd).setHeader("To");

        grid.setDataProvider(SpringDataProviderBuilder.forRepository(repository)
                .withDefaultSort("date", SortDirection.DESCENDING).build());

        grid.asSingleSelect().addValueChangeListener(
                selected -> map.showTrip(selected.getValue())
        );

        add(map, grid);
    }
}
