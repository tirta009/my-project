package myproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingResponse {

    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalRecords;

}
