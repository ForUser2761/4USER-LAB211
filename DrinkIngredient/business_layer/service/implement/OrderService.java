package business_layer.service.implement;

import java.util.List;

import business_layer.entity.Order;
import business_layer.service.IService;
import data_layer.implement.OrderDAO;

public class OrderService implements IService<Order>{
    OrderDAO orderDAO;

    public OrderService() {
        orderDAO = new OrderDAO();
    }

    @Override
    public void create(Order object) throws Exception {
        orderDAO.insert(object);
    }

    @Override
    public Order getById(String service) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public List<Order> search(Order t, String properties) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public void delete(Order object) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public List<Order> findOrdersList() throws Exception {
        orderDAO.clear();
        orderDAO.loadDataFromFile();
        return orderDAO.getOrderList();
    }
    
}
