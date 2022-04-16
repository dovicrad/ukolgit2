import org.junit.jupiter.api.Test;
import shop.*;
import storage.NoItemInStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EshopControllerTest {
    EShopController controller = new EShopController();
    String[] args = {};

    @Test
    public void processTest() throws NoItemInStorage {
        Item[] storageItems = {
                new StandardItem(1, "Dancing Panda v.2", 5000, "GADGETS", 5),
                new StandardItem(2, "Dancing Panda v.3 with USB port", 6000, "GADGETS", 10),
                new StandardItem(3, "Screwdriver", 200, "TOOLS", 5),
                new DiscountedItem(4, "Star Wars Jedi buzzer", 500, "GADGETS", 30, "1.8.2013", "1.12.2013"),
                new DiscountedItem(5, "Angry bird cup", 300, "GADGETS", 20, "1.9.2013", "1.12.2013"),
                new DiscountedItem(6, "Soft toy Angry bird (size 40cm)", 800, "GADGETS", 10, "1.8.2013", "1.12.2013")
        };

        ShoppingCart cart =  new ShoppingCart();
        controller.startEShop();
        for(int i  = 0; i<storageItems.length;i++){
            cart.addItem(storageItems[i]);
        }

        try{
            controller.purchaseShoppingCart(cart,"","");
        }
        catch (Exception e){
            assertEquals("No item in storage",e.getMessage());
        }

        controller.main(args);
        assertEquals(6,cart.getItemsCount());
        cart.removeItem(4);
        cart.removeItem(1);
        assertEquals(4,cart.getItemsCount());
        controller.purchaseShoppingCart(cart,"","");
    }
}
