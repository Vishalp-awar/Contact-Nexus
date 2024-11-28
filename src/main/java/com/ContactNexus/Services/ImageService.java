package com.ContactNexus.Services;

import org.springframework.web.multipart.MultipartFile;

//public interface ImageService {
//
//
//
//    String uploadImage(MultipartFile contactImage);
//
//    String getUrlFromPublicId(String publicid);
//
//}

public interface ImageService {

    String uploadImage(MultipartFile contactImage);

    String getUrlFromPublicId(String publicId);

}