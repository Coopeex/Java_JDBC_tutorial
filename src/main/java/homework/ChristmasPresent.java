package homework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChristmasPresent {
    private int id;
    private String gift;
    private String shop;
    private double price;
    private String recipient;
    private boolean is_bought;
}
