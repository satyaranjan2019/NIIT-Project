package com.niit.DAO;

import java.util.List;

import com.niit.model.OrderDetails;
import com.niit.model.User;

public interface OrderDetailsDao {
	
	public OrderDetails getOrderDetailsByOrderDetailsId(int orderDetails);
	public boolean updateOrderDetails(OrderDetails orderDetails);
	public boolean deleteOrderDetails(OrderDetails orderDetails);
	public boolean insertOrderDetails(OrderDetails orderDetails);
	public List<OrderDetails> getOrderDetailsListByUser (User user);
}

