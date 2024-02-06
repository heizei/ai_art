package com.ai.art.api.model.request.drawing;

import lombok.Data;

/**
 * @author wangBo
 * @version Id: ResultConfigReqDTO.java, v 0.1 2024/2/6 16:43 wangBo Exp $$
 */
@Data
public class ResultConfigReqDTO {

    /**
     * 生成图分辨率
     *
     * 智能文生图支持生成以下分辨率的图片：768:768（1:1）、768:1024（3:4）、1024:768（4:3）、
     * 1024:1024（1:1）、720:1280（9:16）、1280:720（16:9）、768:1280（3:5）、1280:768（5:3）、
     * 1080:1920（9:16）、1920:1080（16:9），不传默认使用768:768
     *
     * 智能图生图支持生成以下分辨率的图片：origin（与输入图分辨率一致）、768:768（1:1）、
     * 768:1024（3:4）、1024:768（4:3），不传默认使用origin，如果指定生成的长宽比与输入图长宽比差异过大可能导致图片内容被裁剪
     */
    private String Resolution;

}