package helpMethods;

import java.util.Random;

public class TestDataFactory {
    public static DTO.ProductRequestDto randGoodAndPrice(Random random) {
        String name = random.nextInt() + "Name" + random.nextInt();
        double num = random.nextDouble(0.0, 1000.0);
        double price = Math.round(num * 1000.0) / 1000.0;

        return new DTO.ProductRequestDto(name, price);
    }

}
