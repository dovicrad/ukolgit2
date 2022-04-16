import archive.PurchasesArchive;
import org.junit.jupiter.api.Test;
import shop.Item;
import shop.Order;
import shop.ShoppingCart;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PurchasesArchiveTest {


    @Test
    public void printItemPurchaseStatisticsTest(){

        PurchasesArchive archive = new PurchasesArchive();
        String customerName,customerAddress;
        customerAddress = customerName= "name";
        ShoppingCart cart = new ShoppingCart();
        Order order;

        setUpConsoleCapture();

        archive.printItemPurchaseStatistics();
        assertEquals("ITEM PURCHASE STATISTICS:\r\n",outputStreamCaptor.toString());

        revertConsoleCapture();

        cart.addItem(new Item(0,"i1",1.1f,"c1") {});
        cart.addItem(new Item(0,"i1",1.1f,"c1") {});
        cart.addItem(new Item(2,null,0f,"c2") {});
        cart.addItem(new Item(-3,null,-100f,null) {});

        setUpConsoleCapture();

        order = new Order(cart, customerName, customerAddress);
        archive.putOrderToPurchasesArchive(order);
        archive.printItemPurchaseStatistics();
        assertEquals("ITEM PURCHASE STATISTICS:\r\nITEM  Item   ID 0   NAME i1   CATEGORY c1   HAS BEEN SOLD 2 TIMES\r\nITEM  Item   ID 2   NAME null   CATEGORY c2   HAS BEEN SOLD 1 TIMES\r\nITEM  Item   ID -3   NAME null   CATEGORY null   HAS BEEN SOLD 1 TIMES\r\n",outputStreamCaptor.toString());

        revertConsoleCapture();
    }

    PrintStream old;
    ByteArrayOutputStream outputStreamCaptor;

    public void setUpConsoleCapture(){
        outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStreamCaptor);
        old = System.out;
        System.setOut(printStream);
    }

    public void revertConsoleCapture(){
        System.out.flush();
        System.setOut(old);
    }

    @Test
    public void getHowManyTimesHasBeenItemSoldTest(){
        PurchasesArchive archive = new PurchasesArchive();
        String customerName,customerAddress;
        customerAddress = customerName= "name";
        ShoppingCart cart = new ShoppingCart();
        Order order;
        Item item1 = new Item(0,"i1",1.1f,"c1") {};
        Item item2 = new Item(-3,null,-100f,null) {};
        cart.addItem(item1);
        cart.addItem(item1);

        order = new Order(cart, customerName, customerAddress);
        archive.putOrderToPurchasesArchive(order);

        assertEquals(2,archive.getHowManyTimesHasBeenItemSold(item1));
        assertEquals(0,archive.getHowManyTimesHasBeenItemSold(item2));
    }

    @Test
    public void putOrderToPurchasesArchiveTest(){

        ArrayList<Order> orderArchive = new ArrayList();
        PurchasesArchive archive = new PurchasesArchive(new HashMap<>(),orderArchive);
        ArrayList<Order> orderArchiveCompare = new ArrayList();

        String customerName,customerAddress;
        customerAddress = customerName= "name";
        ShoppingCart cart1 = new ShoppingCart();
        ShoppingCart cart2 = new ShoppingCart();
        Order order1;
        Order order2;

        Item item1 = new Item(0,"i1",1.1f,"c1") {};
        Item item2 = new Item(-3,null,-100f,null) {};

        cart2.addItem(item1);
        cart2.addItem(item2);

        order1 = new Order(cart1, customerName, customerAddress);
        order2 = new Order(cart2, customerName, customerAddress);

        archive.putOrderToPurchasesArchive(order1);
        archive.putOrderToPurchasesArchive(order2);

        orderArchiveCompare.add(order1);
        orderArchiveCompare.add(order2);

        assertEquals(orderArchiveCompare.get(0), orderArchive.get(0));
        assertEquals(orderArchiveCompare.get(1), orderArchive.get(1));
    }
}
