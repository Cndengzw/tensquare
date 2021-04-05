package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author Deng Zhiwen
 * @date 2021/4/5 15:25
 */
@Data
@AllArgsConstructor
public class PageResult<T> {
    private Long total;
    private List<T> rows;
}
