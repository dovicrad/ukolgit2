import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import shop.StandardItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandardItemTest {

    @ParameterizedTest(name = "expected values should be: id: {0}, name: {1}, price: {2}, category: {3}, loyaltyPoints: {4}")
    @CsvSource({
            "0, NameOfItem0, 0.0, Category0, 0",
            "2, NameOfItem1, 2.2, Category2, 2",
            "3, NameOfItem1, 3.3, Category3, 3",
            "4, NameOfItem1, 4.4, Category0, 0",
            "100, NameOfItem1, 100.1, Category100, 100",
    })
    public void StandardItemConstructorTest(int id, String name, float price, String category, int loyaltyPoints){
        StandardItem item = new StandardItem(id,name,price,category,loyaltyPoints);

        assertEquals(id,item.getID());
        assertEquals(name,item.getName());
        assertEquals(price,item.getPrice());
        assertEquals(category,item.getCategory());
        assertEquals(loyaltyPoints, item.getLoyaltyPoints());
    }

    @ParameterizedTest(name = "expected values should be: id: {0}, name: {1}, price: {2}, category: {3}, loyaltyPoints: {4}")
    @CsvSource({
            "0, NameOfItem0, 0.0, Category0, 0",
            "2, NameOfItem1, 2.2, Category2, 2",
            "3, NameOfItem1, 3.3, Category3, 3",
            "4, NameOfItem1, 4.4, Category0, 0",
            "100, NameOfItem1, 100.1, Category100, 100",
    })
    public void StandardItemCopyTest(int id, String name, float price, String category, int loyaltyPoints){
        StandardItem item = new StandardItem(id,name,price,category,loyaltyPoints);
        StandardItem copy = item.copy();

        assertEquals(item.getID(), copy.getID());
        assertEquals(item.getName(), copy.getName());
        assertEquals(item.getPrice(), copy.getPrice());
        assertEquals(item.getCategory(), copy.getCategory());
        assertEquals(item.getLoyaltyPoints(), copy.getLoyaltyPoints());
    }

    @ParameterizedTest(name = "equals in this case should be {10}")
    @CsvSource({
            "0,0, AA, AA, 0.0, 0.0, C0, C0, 0,0, true",
            "0,1, AA, AA, 0.0, 0.0, C0, C0, 0,0, false",
            "0,0, AA, AB, 0.0, 0.0, C0, C0, 0,0, false",
            "0,0, AA, AA, 0.0, 1.0, C0, C0, 0,0, false",
            "0,0, AA, AA, 0.0, 0.0, C0, C1, 0,0, false",
            "0,0, AA, AA, 0.0, 0.0, C0, C0, 0,1, false",
    })

    public void StandardItemEqualsTest(int id1,int id2, String name1,String name2, float price1, float price2, String category1,String category2, int loyaltyPoints1, int loyaltyPoints2, boolean equals){
        StandardItem item1 = new StandardItem(id1,name1,price1,category1,loyaltyPoints1);
        StandardItem item2 = new StandardItem(id2,name2,price2,category2,loyaltyPoints2);

        boolean isEqual = item1.equals(item2);
        assertEquals(isEqual, equals);

        isEqual = item2.equals(item1);
        assertEquals(isEqual,equals);
    }
}
