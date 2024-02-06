package com.ai.art.api.controller.ai;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.art.api.convert.drawing.AiDrawingConvert;
import com.ai.art.api.model.request.drawing.TextToImageReqDTO;
import com.ai.art.common.response.AjaxResult;
import com.ai.art.proxy.tencent.ai.drawing.TencentAiDrawingProxy;

/**
 * @author wangBo
 * @version Id: AiArtDrawingController.java, v 0.1 2024/2/6 16:04 wangBo Exp $$
 */
@RestController
@RequestMapping("/ai")
public class AiArtDrawingController {

    @Autowired
    private TencentAiDrawingProxy tencentAiDrawingProxy;

    @PostMapping("/textToImag")
    public AjaxResult textToImag(@RequestBody TextToImageReqDTO reqDTO) {

        // todo 转换json结果
        String result = tencentAiDrawingProxy
            .textToImage(AiDrawingConvert.textToImageReqBOConvert(reqDTO));
        return AjaxResult.success(result);
    }

}