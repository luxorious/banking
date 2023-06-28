package com.banking.service.component.implementation;

import com.banking.service.component.ImageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@Slf4j
public class ImageMapperImpl implements ImageMapper {

    @Override
    public byte[] toBytes(MultipartFile image) throws IOException {
        return image.getBytes();
    }
    @Override
    public MultipartFile toFile(byte[] bytes){
        //base64 and crypt
        return null;
    }
}
