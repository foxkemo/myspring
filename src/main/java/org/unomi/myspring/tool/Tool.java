package org.unomi.myspring.tool;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@Getter
@Setter
public class Tool {

    private ObjectMapper objectMapper=new ObjectMapper();

    Tool(){
       this.objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
       this.objectMapper.registerModule(new JavaTimeModule());
        // 避免 LocalDateTime 序列化成时间戳
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


}
