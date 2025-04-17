package at.fhv.sys.hotel;

import at.fhv.sys.hotel.startup.EventReplayService;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.event.Observes;
import org.jboss.logging.Logger;

@QuarkusMain
public class HotelQuerySideApplication {
    void onStart(@Observes StartupEvent ev) {
        Logger.getLogger(HotelQuerySideApplication.class).info("Starting Hotel Query Side Application");

       EventReplayService foobar = new EventReplayService();
       foobar.init();

    }

    public static void main(String[] args) {
        Quarkus.run(args);
    }
}
