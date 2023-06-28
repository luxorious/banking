package com.banking.service.component;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageMapper {

    byte[] toBytes(MultipartFile image) throws IOException;
    MultipartFile toFile(byte[] bytes);
}
