package M7_Activity1;

import M7_Activity1.controller.MovieController;
import M7_Activity1.service.MovieService;
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(4567);
        MovieService movieService = new MovieService();
        MovieController movieController = new MovieController(movieService);
        movieController.registerRoutes();
    }
}
