package com.ai.art.api.model.request.drawing;

import java.util.List;

import lombok.Data;

/**
 * @author wangBo
 * @version Id: TextToImageReqDTO.java, v 0.1 2024/2/6 16:13 wangBo Exp $$
 */
@Data
public class TextToImageReqDTO {

    /**
     * 文本描述。
     * 算法将根据输入的文本智能生成与之相关的图像。建议详细描述画面主体、细节、场景等，文本描述越丰富，生成效果越精美。
     * 不能为空，推荐使用中文。最多可传256个 utf-8 字符。
     */
    private String             Prompt;

    /**
     * 反向文本描述。
     * 用于一定程度上从反面引导模型生成的走向，减少生成结果中出现描述内容的可能，但不能完全杜绝。
     * 推荐使用中文。最多可传256个 utf-8 字符
     */
    private String             NegativePrompt;

    /**
     * 绘画风格。
     * 请在 智能文生图风格列表 中选择期望的风格，传入风格编号。
     * 推荐使用且只使用一种风格。不传默认使用201（日系动漫风格）
     */
    private List<String>       Styles;

    /**
     * 生成图结果的配置，包括输出图片分辨率和尺寸等。
     * 支持生成以下分辨率的图片：768:768（1:1）、768:1024（3:4）、1024:768（4:3）、
     * 1024:1024（1:1）、720:1280（9:16）、1280:720（16:9）、768:1280（3:5）、
     * 1280:768（5:3）、1080:1920（9:16）、1920:1080（16:9），不传默认使用768:768
     */
    private ResultConfigReqDTO ResultConfig;

    /**
     * 为生成结果图添加标识的开关，默认为1。
     * 1：添加标识。
     * 0：不添加标识。
     * 其他数值：默认按1处理。
     * 建议您使用显著标识来提示结果图使用了 AI 绘画技术，是 AI 生成的图片
     */
    private Integer            LogoAdd;

    /**
     * 标识内容设置。
     * 默认在生成结果图右下角添加“图片由 AI 生成”字样，您可根据自身需要替换为其他的标识图片
     */
    private LogoParamReqDTO    LogoParam;

    /**
     * 返回图像方式（base64 或 url) ，二选一，默认为 base64。url 有效期为1小时
     */
    private String             RspImgType;

}