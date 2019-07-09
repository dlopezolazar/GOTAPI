package py.una.pol.got.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterVO {

    @JsonProperty(value = "character_name", required = true)
    private String characterName;
    @JsonProperty(value = "house_name", required = true)
    private String houseName;
}
