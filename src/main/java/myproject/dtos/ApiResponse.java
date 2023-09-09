package myproject.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Builder
public class ApiResponse<T> {

    private T data;

    private PagingResponse paging;

    private String error;

    public static ApiResponse error(final String message){
        final ApiResponse apiResponse = new ApiResponse();
        apiResponse.setError(message);
        return apiResponse;
    }

}
