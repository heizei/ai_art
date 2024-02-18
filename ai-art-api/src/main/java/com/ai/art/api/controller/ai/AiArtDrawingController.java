package com.ai.art.api.controller.ai;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
public class AiArtDrawingController {

    @Autowired
    private TencentAiDrawingProxy tencentAiDrawingProxy;

    @PostMapping("/textToImag")
    public AjaxResult textToImag(@RequestBody TextToImageReqDTO reqDTO) {

        System.out.println(reqDTO);

        String result = tencentAiDrawingProxy
            .textToImage(AiDrawingConvert.textToImageReqBOConvert(reqDTO));

        JsonObject jsonObject = (JsonObject) new JsonParser().parse(result);
        String data = jsonObject.get("Response").getAsJsonObject().get("ResultImage").getAsString();

        return AjaxResult.success(data);
    }

}