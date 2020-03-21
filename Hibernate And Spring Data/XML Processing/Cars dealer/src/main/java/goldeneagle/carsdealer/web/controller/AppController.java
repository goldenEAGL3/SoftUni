package goldeneagle.carsdealer.web.controller;

import goldeneagle.carsdealer.domain.dto.queryDTOs.query1.OrderedCustomerModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query1.OrderedCustomersModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query2.CarFromMakeModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query2.CarFromMakeModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query3.LocalSupplierModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query3.LocalSupplierModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query4.CarsAndPartsModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query4.CarsAndPartsModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query5.CustomersTotalSalesModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query5.CustomersTotalSalesModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query6.SalesWithAppliedDiscountModelView;
import goldeneagle.carsdealer.domain.dto.queryDTOs.query6.SalesWithAppliedDiscountModelViewWrapper;
import goldeneagle.carsdealer.domain.dto.seedDTOs.*;
import goldeneagle.carsdealer.service.interfaces.*;
import goldeneagle.carsdealer.utils.interfaces.FileUtil;
import goldeneagle.carsdealer.utils.interfaces.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {

    private static final String SUPPLIERS_FILE_PATH = "/files/suppliers.xml";
    private static final String PARTS_FILE_PATH = "/files/parts.xml";
    private static final String CUSTOMERS_FILE_PATH = "/files/customers.xml";
    private static final String CARS_FILE_PATH = "/files/cars.xml";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    private final FileUtil fileUtil;
    private final Serializer serializer;

    private String result;
    @Autowired
    public AppController(SupplierService supplierService,
                         PartService partService,
                         CarService carService,
                         CustomerService customerService,
                         SaleService saleService,
                         FileUtil fileUtil,
                         Serializer serializer) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.fileUtil = fileUtil;
        this.serializer = serializer;
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
        SalesWithAppliedDiscountModelViewWrapper sales = this.saleService.getAllSalesWithAppliedDiscount();
        this.result = this.serializer.serialize(sales);
        this.fileUtil.writeToFile(this.result, "output6");
    }

    private void getTotalSalesByCustomer() throws IOException {
       CustomersTotalSalesModelViewWrapper customers = this.customerService.getTotalSalesByCustomer();
        this.result = this.serializer.serialize(customers);
        this.fileUtil.writeToFile(this.result, "output5");
    }


    private void getAllCarsAndTheirParts() throws IOException {
        CarsAndPartsModelViewWrapper cars = this.carService.getCarsAndParts();
        this.result = this.serializer.serialize(cars);
        this.fileUtil.writeToFile(this.result, "output4");
    }


    private void getLocalSuppliers() throws IOException {
        LocalSupplierModelViewWrapper suppliers = this.supplierService.getAllLocalSuppliers();
        this.result = this.serializer.serialize(suppliers);
        this.fileUtil.writeToFile(this.result, "output3");
    }


    private void getCarsFromMake() throws IOException {
        CarFromMakeModelViewWrapper cars = this.carService.getCarsFromMake("Toyota");
        this.result = this.serializer.serialize(cars);
        this.fileUtil.writeToFile(this.result, "output2");
    }

    private void getOrderedCustomers() throws IOException {
        OrderedCustomersModelViewWrapper customer = this.customerService.getOrderedCustomers();
        this.result = this.serializer.serialize(customer);
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
        CustomerSeedDTOWrapper customerSeedDTOs = this.serializer.deserialize(CustomerSeedDTOWrapper.class, AppController.CUSTOMERS_FILE_PATH);
        this.customerService.seedCustomers(customerSeedDTOs);
    }

    private void importCars() throws IOException {
        if(this.carService.getCarCount() > 0) {
            return;
        }
        String content = this.fileUtil.getContent(AppController.CARS_FILE_PATH);
        CarSeedDTOWrapper carsSeedDTOs = this.serializer.deserialize(CarSeedDTOWrapper.class, AppController.CARS_FILE_PATH);
        this.carService.seedCars(carsSeedDTOs);
    }

    private void importParts() throws IOException {
        if(this.partService.getSupplierCount() > 0) {
            return;
        }

        PartsSeedDTOWrapper partsSeedDTOs = this.serializer.deserialize(PartsSeedDTOWrapper.class, AppController.PARTS_FILE_PATH);
        this.partService.seedParts(partsSeedDTOs);
    }

    private void importSuppliers() throws IOException {
        if(this.supplierService.getSupplierCount() > 0) {
            return;
        }
        SupplierSeedDTOWrapper supplierSeedDTOs = this.serializer.deserialize(SupplierSeedDTOWrapper.class, AppController.SUPPLIERS_FILE_PATH);
        this.supplierService.seedSuppliers(supplierSeedDTOs);

    }
}
