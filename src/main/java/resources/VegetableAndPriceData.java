package resources;

import org.testng.annotations.DataProvider;

public class VegetableAndPriceData {

    @DataProvider(name = "vegetable")
    public Object[] getVegetable(){
        //[3] = Row count
        //[2] = Column count
        Object[][] vegetableData =new Object[3][2];

        // Row 0 -> 1
        vegetableData[0][0] = "Rice";   vegetableData[0][1] = "37";

        // Row 1 -> 2
        vegetableData[1][0] = "Tomato"; vegetableData[1][1] = "37";

        // Row 2 -> 3
        vegetableData[2][0] = "Carrot"; vegetableData[2][1] = "34";

        return vegetableData;
    }

}
