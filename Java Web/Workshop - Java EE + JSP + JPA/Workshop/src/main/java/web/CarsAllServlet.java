package web;

import domain.service.CarServiceModel;
import domain.view.ViewModel;
import service.CarService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cars/all")
public class CarsAllServlet extends HttpServlet {
    private final CarService carService;

    @Inject
    public CarsAllServlet(CarService carService) {
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CarServiceModel> allCars = carService.getAll();
        ViewModel<List<CarServiceModel>> model = new ViewModel<>(allCars);
        req.setAttribute("model", model);
        req.getRequestDispatcher("/cars-all.jsp").forward(req, resp);

    }
}
