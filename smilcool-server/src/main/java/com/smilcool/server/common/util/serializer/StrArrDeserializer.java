package com.smilcool.server.common.util.serializer;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Angus
 * @date 2019/5/13
 */
@Slf4j
public class StrArrDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (p != null && StringUtils.isNotEmpty(p.getText())) {
            String value = p.getText();
            log.debug("value: {}", value);
            return value;
        } else {
            return null;
        }
    }
}
