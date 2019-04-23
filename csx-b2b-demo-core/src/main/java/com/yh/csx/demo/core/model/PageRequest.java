package com.yh.csx.demo.core.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageRequest implements Serializable {

	private static final long serialVersionUID = 8357212825359633398L;

	@ApiModelProperty("分页页码")
    private Integer pageNum = 1;

    @ApiModelProperty("分页大小")
    private Integer pageSize = 10;
}
