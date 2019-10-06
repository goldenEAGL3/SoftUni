package alararestaurant.service;

import alararestaurant.domain.dtos.ImportXML.OrderDto;
import alararestaurant.domain.dtos.ImportXML.OrderItemDto;
import alararestaurant.domain.dtos.ImportXML.OrderWrapperDto;
import alararestaurant.domain.entities.*;
import alararestaurant.repository.*;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;

import alararestaurant.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String ORDERS_IMPORT_XML_FILE_PATH =
            "D:\\Java\\Hibernate\\AlaraRestaurant\\AlaraRestaurant\\src\\main\\resources\\files\\orders.xml";
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;
    private final PositionRepository positionRepository;

    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, EmployeeRepository employeeRepository, ItemRepository itemRepository, PositionRepository positionRepository, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.positionRepository = positionRepository;


        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {
        return this.fileUtil.readFile(ORDERS_IMPORT_XML_FILE_PATH);
    }

    @Override
    public String importOrders() throws JAXBException {
        StringBuilder sb = new StringBuilder();
        OrderWrapperDto ordersDto = this.xmlParser.importXML(OrderWrapperDto.class, ORDERS_IMPORT_XML_FILE_PATH);

//        orderDto:
        for (OrderDto orderDto : ordersDto.getOrder()) {

            Employee orderEmployee = this.employeeRepository.findEmployeeByName(orderDto.getEmployee());

            boolean allItemsExist = true;
            for (OrderItemDto item : orderDto.getItems().getOrderItem()) {
                Item itemToFind = this.itemRepository.findItemByName(item.getName());
                if (itemToFind == null) {
//                    continue orderDto;
                    allItemsExist = false;
                    break;
                }
            }
            if (!this.validationUtil.isValid(orderDto) || orderEmployee == null || !allItemsExist) {
                sb.append("Invalid data format.").append(System.lineSeparator());
                continue;
            }

            Order order = this.modelMapper.map(orderDto, Order.class);

            order.setEmployee(orderEmployee);

            List<OrderItem> orderItems = order.getOrderItems();
            for (int i = 0; i < orderItems.size(); i++) {
                OrderItem orderItem = orderItems.get(i);
                orderItem.setOrder(order);
                Item item = this.itemRepository.findItemByName(order.getOrderItems().get(i).getItem().getName());
                orderItem.setItem(item);
            }

            this.orderRepository.saveAndFlush(order);
            sb.append(String.format("Order for %s on %s added.%n", order.getCustomer(), order.getDateTime()));
        }

        return sb.toString();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        StringBuilder sb = new StringBuilder();
        Position neededPosition = this.positionRepository.findPositionByName("Burger Flipper");
        List<Order> ordersList = this.orderRepository.findAllByEmployeePosition(neededPosition)
                .stream()
                .sorted(Comparator.comparing((Order a) -> a.getEmployee().getName())
                        .thenComparingInt(BaseEntity::getId))
                .collect(Collectors.toList());
        for (Order order : ordersList) {
            sb.append(String.format("Name: %s%n" +
                    "Orders:%n" +
                    "   Customer: %s%n" +
                    "   Items:%n",
                    order.getEmployee().getName(),
                    order.getCustomer()) );
            for (OrderItem orderItem : order.getOrderItems()) {
                sb.append(String.format("       Name: %s%n" +
                        "       Price: %.2f%n" +
                        "       Quantity: %d%n",
                        orderItem.getItem().getName(),
                        orderItem.getItem().getPrice(),
                        orderItem.getQuantity()))
                        .append(System.lineSeparator());
            }

        }
        return sb.toString().trim();
    }


}
