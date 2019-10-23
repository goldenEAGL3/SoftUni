package web;

import domain.entity.enums.Engine;
import domain.service.CarServiceModel;
import service.CarService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cars/create")
public class CarsCreateServlet extends HttpServlet {

    private final CarService carService;

    @Inject
    public CarsCreateServlet(CarService carService) {
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/cars-create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String year = req.getParameter("year");
        Engine engine = Engine.valueOf(req.getParameter("engine").toUpperCase());
        String username = (String) req.getSession().getAttribute("username");
        CarServiceModel car = carService.create(brand, model, year, engine, username);

        if (car == null) {
            resp.sendRedirect("/cars/create");
        } else {
            resp.sendRedirect("/home");

        }

    }
}
