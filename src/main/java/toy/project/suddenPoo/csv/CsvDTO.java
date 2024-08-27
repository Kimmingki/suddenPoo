package toy.project.suddenPoo.csv;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Data
public class CsvDTO {

    private String toiletName;
    private String roadName;
    private String latitude;
    private String longitude;

    public static List<String> getFieldNames() {
        Field[] declaredFields = CsvDTO.class.getDeclaredFields();
        List<String> result = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            result.add(declaredField.getName());
        }

        return result;
    }
}
