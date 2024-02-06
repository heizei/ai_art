package com.ai.art.api.convert.drawing;

import com.ai.art.api.model.request.drawing.LogoParamReqDTO;
import com.ai.art.api.model.request.drawing.LogoRectReqDTO;
import com.ai.art.api.model.request.drawing.ResultConfigReqDTO;
import com.ai.art.api.model.request.drawing.TextToImageReqDTO;
import com.ai.art.proxy.tencent.ai.model.request.drawing.LogoParamReqBO;
import com.ai.art.proxy.tencent.ai.model.request.drawing.LogoRectReqBO;
import com.ai.art.proxy.tencent.ai.model.request.drawing.ResultConfigReqBO;
import com.ai.art.proxy.tencent.ai.model.request.drawing.TextToImageReqBO;

/**
 * @author wangBo
 * @version Id: AiDrawingConvert.java, v 0.1 2024/2/6 16:39 wangBo Exp $$
 */
public class AiDrawingConvert {

    public static TextToImageReqBO textToImageReqBOConvert(TextToImageReqDTO reqDTO) {
        TextToImageReqBO textToImageReqBO = new TextToImageReqBO();
        textToImageReqBO.setPrompt(reqDTO.getPrompt());
        textToImageReqBO.setNegativePrompt(reqDTO.getNegativePrompt());
        textToImageReqBO.setStyles(reqDTO.getStyles());
        textToImageReqBO.setResultConfig(resultConfigReqBOConvert(reqDTO.getResultConfig()));
        textToImageReqBO.setLogoAdd(reqDTO.getLogoAdd());
        textToImageReqBO.setLogoParam(logoParamReqBOConvert(reqDTO.getLogoParam()));
        textToImageReqBO.setRspImgType(reqDTO.getRspImgType());
        return textToImageReqBO;
    }

    private static ResultConfigReqBO resultConfigReqBOConvert(ResultConfigReqDTO reqDTO) {
        if (reqDTO == null) {
            return null;
        }
        ResultConfigReqBO resultConfigReqBO = new ResultConfigReqBO();
        resultConfigReqBO.setResolution(reqDTO.getResolution());
        return resultConfigReqBO;
    }

    private static LogoParamReqBO logoParamReqBOConvert(LogoParamReqDTO logoParamReqDTO) {
        if (logoParamReqDTO == null) {
            return null;
        }
        LogoParamReqBO logoParamReqBO = new LogoParamReqBO();
        logoParamReqBO.setLogoUrl(logoParamReqDTO.getLogoUrl());
        logoParamReqBO.setLogoImage(logoParamReqDTO.getLogoImage());
        logoParamReqBO.setLogoRect(logoRectReqBOConvert(logoParamReqDTO.getLogoRect()));
        return logoParamReqBO;
    }

    private static LogoRectReqBO logoRectReqBOConvert(LogoRectReqDTO logoRectReqDTO) {
        if (logoRectReqDTO == null) {
            return null;
        }
        LogoRectReqBO logoRectReqBO = new LogoRectReqBO();
        logoRectReqBO.setX(logoRectReqDTO.getX());
        logoRectReqBO.setY(logoRectReqDTO.getY());
        logoRectReqBO.setWidth(logoRectReqDTO.getWidth());
        logoRectReqBO.setHeight(logoRectReqDTO.getHeight());
        return logoRectReqBO;
    }
}