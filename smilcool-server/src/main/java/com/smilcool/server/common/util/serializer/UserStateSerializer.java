package com.smilcool.server.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.smilcool.server.common.enums.UserState;

import java.io.IOException;

/**
 * @author Angus
 * @date 2019/3/20
 */
public class UserStateSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(UserState.desc(value));
    }
}
