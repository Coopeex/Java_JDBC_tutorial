package teamWork.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Guest {
    private int id;
    private String name;
    private int age;
    private String emailAddress;
    private String nationality;
}
