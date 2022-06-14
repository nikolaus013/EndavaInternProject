package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUserDto {
    private String username;
    @Size(min = 3,max = 10,message = "Criteria not met")
    private String name;
    private String surname;
}
