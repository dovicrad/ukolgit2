import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import shop.Item;
import storage.ItemStock;
import storage.NoItemInStorage;
import storage.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemStockTest {

    @ParameterizedTest(name = "count should be 0")
    @CsvSource({
            "1,i1,1.1f,c1",
    })
    public void constructorTest(int id, String name, float price, String category){
        Item item = new Item(id,"name",price,category){};
        Storage storage = new Storage();
        assertEquals(0,storage.getItemCount(item));


    }

    @ParameterizedTest(name = "count should be {4} and then 0")
    @CsvSource({
            "1,i1,1.1f,c1,5",
            "2,i2,2.2f,c2,0",
            "3,i3,3.3f,c3,-5",
            "4,i4,4.4f,c4,10",
    })
    public void countChangeTest(int id, String name, float price, String category, int count) throws NoItemInStorage {
        Item item = new Item(id,"name",price,category){};
        Storage storage = new Storage();
        storage.insertItems(item, count);
        assertEquals(count, storage.getItemCount(item));
        storage.removeItems(item, count);
        assertEquals(0, storage.getItemCount(item));
    }
}
