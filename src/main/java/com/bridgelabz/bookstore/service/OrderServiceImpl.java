package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Cart;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CartRepository cartRepository;

    @Autowired
    IBookService bookService;

    //@Override
//    public Order placeOrder(Long userId, OrderDTO orderDTO) {
//        User user = userRepository.findById(userId).orElse(null);
//        Book book = bookRepository.findById(orderDTO.getBookId()).orElse(null);
//
//        if (user != null && book != null) {
//
//            //Price Calculations
//            double orderPrice = book.getPrice() * orderDTO.getQuantity();
//
//            //Remove Quantity of Books after Order Placed
//            book.setQuantity(book.getQuantity()-orderDTO.getQuantity());
//            Order order = new Order(user,book,orderPrice,orderDTO);
//            orderRepository.save(order);
//
//
//            //Delete Cart By Id
//            cartRepository.deleteById(orderDTO.getCartId());
//
//            return order;
//        }
//
//        return null;
//    }

    @Override
    public List<Order> placeOrder(Long userId,String address) {
        List<Cart> cartList = cartRepository.getCartListByUserId(userId);

        if (cartList != null){
            List<Order> orderList = new ArrayList<>();

            for (Cart cartItem : cartList){
                Book book = cartItem.getBook();
                User user = cartItem.getUser();
                double price = cartItem.getTotalPrice();
                OrderDTO orderDTO = new OrderDTO();
                orderDTO.setAddress(address);
                orderDTO.setQuantity(cartItem.getQuantity());
                Order order = new Order(user, book, price, orderDTO);
                orderList.add(order);

            }

            List<Order> placedOrder = orderRepository.saveAll(orderList);

            if(placedOrder.size() != 0){
                cartRepository.deleteAll(cartList);
                for (Cart cartItem : cartList){
                    bookService.updateBookQuantity(cartItem.getBook().getBookId(),cartItem.getBook().getQuantity() - cartItem.getQuantity());
                }
            }

            return placedOrder;

        }
        return null;
    }

    @Override
    public String cancelOrder(Long orderId, Long userId) {

        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Order order = orderRepository.findById(orderId).orElse(null);
            Book book = bookRepository.findById(order.getBook().getBookId()).orElse(null);

            if (order != null) {
                order.setCancel(true);
                orderRepository.save(order);

                //change book quantity after order cancelled
                //book.setQuantity(book.getQuantity() + order.getQuantity());
                bookService.updateBookQuantity(order.getBook().getBookId(), order.getBook().getQuantity()+order.getQuantity());

                return "Order Cancelled";
            }
        }

        return null;
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<Order> order = orderRepository.findAllByUserId(userId);
            return order;
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
