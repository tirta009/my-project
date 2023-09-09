package myproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.entities.MstUser;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MstUserResponse {

    private Long id;
    private String username;
    private String email;
    public static MstUserResponse entityToDto(MstUser entity){

        MstUserResponse response = new MstUserResponse();
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setEmail(entity.getEmail());

        return response;
    }

}
