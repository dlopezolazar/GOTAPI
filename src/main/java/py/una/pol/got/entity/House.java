package py.una.pol.got.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {

    @JsonProperty(value = "id_house", required = false)
    private Integer idHouse;
    @JsonProperty(value = "name", required = true)
    private String name;

}
