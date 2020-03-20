package goldeneagle.carsdealer.web.controller;

import com.google.gson.Gson;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query1.OrderedCustomerModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query2.CarFromMakeModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query3.LocalSupplierModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query4.CarsAndPartsModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query5.CustomersTotalSalesModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query6.SalesWithAppliedDiscountModelView;
import goldeneagle.carsdealer.domain.dto.seedDTOs.CarSeedDTO;
import goldeneagle.carsdealer.domain.dto.seedDTOs.CustomerSeedDTO;
import goldeneagle.carsdealer.domain.dto.seedDTOs.PartsSeedDTO;
import goldeneagle.carsdealer.domain.dto.seedDTOs.SupplierSeedDTO;
import goldeneagle.carsdealer.service.interfaces.*;
import goldeneagle.carsdealer.utils.interfaces.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {

    private static final String SUPPLIERS_FILE_PATH = "D:\\Java\\Hibernate\\JSON PROCESSING\\Carsdealer\\src\\main\\resources\\files\\suppliers.json";
    private static final String PARTS_FILE_PATH = "D:\\Java\\Hibernate\\JSON PROCESSING\\Carsdealer\\src\\main\\resources\\files\\parts.json";
    private static final String CUSTOMERS_FILE_PATH = "D:\\Java\\Hibernate\\JSON PROCESSING\\ProductShop\\src\\main\\resources\\files\\customers.json";
    private static final String CARS_FILE_PATH = "D:\\Java\\Hibernate\\JSON PROCESSING\\ProductShop\\src\\main\\resources\\files\\cars.json";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    private final FileUtil fileUtil;
    private final Gson gson;

    private String result;
    @Autowired
    public AppController(SupplierService supplierService,
                         PartService partService,
                         CarService carService,
                         CustomerService customerService,
                         SaleService saleService,
                         FileUtil fileUtil,
                         Gson gson) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }


    @Override
    public void run(String... args) throws Exception {
        this.seedDatabase();
        this.getOrderedCustomers();
        this.getCarsFromMake();
        this.getLocalSuppliers();
        this.getAllCarsAndTheirParts();
        this.getTotalSalesByCustomer();
        this.getSalesWithAppliedDiscount();
    }

    private void getSalesWithAppliedDiscount() throws IOException {
        List<SalesWithAppliedDiscountModelView> sales = this.saleService.getAllSalesWithAppliedDiscount();
        this.result = this.gson.toJson(sales);
        this.fileUtil.writeToFile(this.result, "output6");
    }

    private void getTotalSalesByCustomer() throws IOException {
        List<CustomersTotalSalesModelView> customers = this.customerService.getTotalSalesByCustomer();
        this.result = this.gson.toJson(customers);
        this.fileUtil.writeToFile(this.result, "output5");
    }


    private void getAllCarsAndTheirParts() throws IOException {
        List<CarsAndPartsModelView> cars = this.carService.getCarsAndParts();
        this.result = this.gson.toJson(cars);
        this.fileUtil.writeToFile(this.result, "output4");
    }


    private void getLocalSuppliers() throws IOException {
        List<LocalSupplierModelView> suppliers = this.supplierService.getAllLocalSuppliers();
        this.result = this.gson.toJson(suppliers);
        this.fileUtil.writeToFile(this.result, "output3");
    }


    private void getCarsFromMake() throws IOException {
        List<CarFromMakeModelView> cars = this.carService.getCarsFromMake("Toyota");
        this.result = this.gson.toJson(cars);
        this.fileUtil.writeToFile(this.result, "output2");
    }

    private void getOrderedCustomers() throws IOException {
        List<OrderedCustomerModelView> customer = this.customerService.getOrderedCustomers();
        this.result = this.gson.toJson(customer);
        this.fileUtil.writeToFile(this.result, "output1");
    }

    private void seedDatabase() throws IOException {
        this.importSuppliers();
        this.importParts();
        this.importCars();
        this.importCustomers();
        this.importSales();
    }

    private void importSales() {
        if(this.saleService.getSalesCount() > 0) {
            return;
        }
        this.saleService.seedSales();
    }

    private void importCustomers() throws IOException {
        if(this.customerService.getCustomerCount() > 0) {
            return;
        }
        String content = this.fileUtil.getContent(AppController.CUSTOMERS_FILE_PATH);
        CustomerSeedDTO[] customerSeedDTOs = this.gson.fromJson(content, CustomerSeedDTO[].class);
        this.customerService.seedCustomers(customerSeedDTOs);
    }

    private void importCars() throws IOException {
        if(this.carService.getCarCount() > 0) {
            return;
        }
        String content = this.fileUtil.getContent(AppController.CARS_FILE_PATH);
        CarSeedDTO[] carsSeedDTOs = this.gson.fromJson(content, CarSeedDTO[].class);
        this.carService.seedCars(carsSeedDTOs);
    }

    private void importParts() throws IOException {
        if(this.partService.getSupplierCount() > 0) {
            return;
        }

        String content = this.fileUtil.getContent(AppController.PARTS_FILE_PATH);
        PartsSeedDTO[] partsSeedDTOs = this.gson.fromJson(content, PartsSeedDTO[].class);
        this.partService.seedParts(partsSeedDTOs);
    }

    private void importSuppliers() throws IOException {
        if(this.supplierService.getSupplierCount() > 0) {
            return;
        }
        String content = this.fileUtil.getContent(AppController.SUPPLIERS_FILE_PATH);
        SupplierSeedDTO[] supplierSeedDTOs = this.gson.fromJson(content, SupplierSeedDTO[].class);
        this.supplierService.seedSuppliers(supplierSeedDTOs);

    }
}
