import org.junit.jupiter.api.Test;
import shop.Item;
import shop.Order;
import shop.ShoppingCart;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderTest {
    @Test
    public void OrderConstructorTest(){
        ShoppingCart cart = new ShoppingCart();

        String customerName = null;
        String customerAddress = null;
        Order order = new Order(cart, customerName, customerAddress);

        assertEquals(cart.getCartItems(),order.getItems());

        cart.addItem(new Item(0,"i1",1.1f,"c1") {});
        cart.addItem(new Item(2,null,0f,"c2") {});
        cart.addItem(new Item(-3,null,-100f,null) {});


        for(int i = 0; i < 4; i++){
            switch (i){
                case 0: {
                    customerName = "John";
                    customerAddress = "Wick";
                    order = new Order(cart, customerName, customerAddress);
                    break;
                }
                case 1:{
                    customerName = null;
                    customerAddress = null;
                    order = new Order(cart, customerName, customerAddress);
                    break;
                }
                case 2: {
                    customerName = "John";
                    customerAddress = "Wick";
                    order = new Order(cart, customerName, customerAddress, 100);
                    break;
                }
                case 3:{
                    customerName = null;
                    customerAddress = null;
                    order = new Order(cart, customerName, customerAddress, 100);
                    break;
                }
            }
            assertEquals(cart.getCartItems(),order.getItems());
            assertEquals(customerName,order.getCustomerName());
            assertEquals(customerAddress,order.getCustomerAddress());
        }
    }
}
