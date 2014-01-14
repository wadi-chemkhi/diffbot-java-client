package com.diffbot.clients;

import com.diffbot.clients.models.Product;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by wadi chemkhi on 14/01/14.
 * Email : wadi.chemkhi@gmail.com
 */
public class ProductApiTest {


    private String testToken="353883355a5c7ff1793b14f81e19c359";

    @Test
    public void getProductTest() throws IOException {
        DiffbotClient client = new DiffbotClient(testToken);
        List<Product> l= (List) client.getProducts(Product.class, "http://www.overstock.com/Home-Garden/iRobot-650-Roomba-Vacuuming-Robot/7886009/product.html");
        for(Product p :l)
            System.out.println(p.toString());
    }
}
