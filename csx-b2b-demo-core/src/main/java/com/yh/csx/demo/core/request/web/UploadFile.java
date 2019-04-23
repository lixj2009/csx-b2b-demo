package com.yh.csx.demo.core.request.web;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * UploadFiles class
 *
 * @author huyalin
 * @date 2019/3/7
 */
@Data
public class UploadFile {

    @ApiModelProperty("附件url")
    private String fileUrl;

    @ApiModelProperty("附件名称")
    private String fileName;

    public UploadFile() {
    }

    public UploadFile(String fileUrl, String fileName) {
        this.fileUrl = fileUrl;
        this.fileName = fileName;
    }
}
