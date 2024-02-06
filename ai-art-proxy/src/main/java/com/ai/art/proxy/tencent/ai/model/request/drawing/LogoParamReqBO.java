package com.ai.art.proxy.tencent.ai.model.request.drawing;

import lombok.Data;

/**
 * @author wangBo
 * @version Id: LogoParamReqBO.java, v 0.1 2024/2/6 16:18 wangBo Exp $$
 */
@Data
public class LogoParamReqBO {

    /**
     * 水印url
     */
    private String         LogoUrl;
    /**
     * 水印base64，url和base64二选一传入
     */
    private String         LogoImage;
    /**
     * 水印图片位于融合结果图中的坐标，将按照坐标对标识图片进行位置和大小的拉伸匹配
     */
    private LogoRectReqBO LogoRect;

}