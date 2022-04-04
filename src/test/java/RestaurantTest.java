import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @BeforeEach
    public void initializeRestaurantObject()
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("20:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant restaurant_mocked = Mockito.spy(restaurant);
        LocalTime betweenTime = LocalTime.parse("11:00:00");
        Mockito.when(restaurant_mocked.getCurrentTime()).thenReturn(betweenTime);

        Boolean isResturantOpen = restaurant_mocked.isRestaurantOpen();
        assertTrue(isResturantOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant restaurant_mocked = Mockito.spy(restaurant);
        LocalTime outisdeTime = LocalTime.parse("09:00:00");
        Mockito.when(restaurant_mocked.getCurrentTime()).thenReturn(outisdeTime);

        Boolean isResturantOpen = restaurant_mocked.isRestaurantOpen();
        assertFalse(isResturantOpen);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>Getting Total Price<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void totalBill_should_be_zero_when_no_items_are_selected()
    {
        ArrayList<String> selectedItems = new ArrayList<String>();
        Integer totalBill = restaurant.getTotalPrice(selectedItems);
        assertEquals(0, totalBill);
    }

    @Test
    public void totalBill_should_be_388_when_sweetCorn_and_Lasagna_are_selected()
    {
        ArrayList<String> selectedItems = new ArrayList<String>();
        selectedItems.add("Sweet corn soup");
        selectedItems.add("Vegetable lasagne");
        Integer totalBill = restaurant.getTotalPrice(selectedItems);
        assertEquals(388,totalBill);

    }

    @Test
    public void totalBill_should_be_119_when_sweetCorn_is_selected()
    {
        ArrayList<String> selectedItems = new ArrayList<String>();
        selectedItems.add("Sweet corn soup");

        Integer totalBill = restaurant.getTotalPrice(selectedItems);
        assertEquals(119,totalBill);

    }

    @Test
    public void totalBill_should_be_269_when_Lasagna_are_selected()
    {
        ArrayList<String> selectedItems = new ArrayList<String>();
        selectedItems.add("Vegetable lasagne");

        Integer totalBill = restaurant.getTotalPrice(selectedItems);
        assertEquals(269,totalBill);

    }
    //<<<<<<<<<<<<<<<<<<<<<<<Getting Total Price>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}