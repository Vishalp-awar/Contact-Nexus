//package com.ContactNexus.Validators;
//
//
//import org.springframework.web.multipart.MultipartFile;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {
//
//    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2; // 2MB
//
//    // type
//
//    // height
//
//    // width
//
//    @Override
//    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
//
//        if (file == null || file.isEmpty() ) {
//
//             context.disableDefaultConstraintViolation();
//             context.buildConstraintViolationWithTemplate("File cannot be  empty").addConstraintViolation();
//            return true;
//
//        }
//
//        // file size
//
////        System.out.println("file size: " + file.getSize());
//
//        if (file.getSize() > MAX_FILE_SIZE) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate("File size should be less than 2MB").addConstraintViolation();
//            return false;
//        }
//
//        // resolution
//
//        // try {
//        // BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
//
//        // if(bufferedImage.getHe)
//
//        // } catch (IOException e) {
//        // // TODO Auto-generated catch block
//        // e.printStackTrace();
//        // }
//        return true;
//    }
//
//}
package com.ContactNexus.Validators;

import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2; // 2MB
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif");
    private static final int MIN_WIDTH = 100;
    private static final int MIN_HEIGHT = 100;
    private static final int MAX_WIDTH = 1920;
    private static final int MAX_HEIGHT = 1080;

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate("File cannot be empty").addConstraintViolation();
                return true;
        }

        // Check file size
        if (file.getSize() > MAX_FILE_SIZE) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size should be less than 2MB").addConstraintViolation();
            return false;
        }

        // Check file type
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase() : "";
        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Only JPG, JPEG, PNG, and GIF files are allowed").addConstraintViolation();
            return false;
        }

        // Check image resolution
        try {
            BufferedImage img = ImageIO.read(file.getInputStream());
            if (img == null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Invalid image file").addConstraintViolation();
                return false;
            }
            int width = img.getWidth();
            int height = img.getHeight();

            if (width < MIN_WIDTH || height < MIN_HEIGHT) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Image resolution is too low. Minimum dimensions are " + MIN_WIDTH + "x" + MIN_HEIGHT).addConstraintViolation();
                return false;
            }

            if (width > MAX_WIDTH || height > MAX_HEIGHT) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Image resolution is too high. Maximum dimensions are " + MAX_WIDTH + "x" + MAX_HEIGHT).addConstraintViolation();
                return false;
            }
        } catch (IOException e) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Error processing image file").addConstraintViolation();
            return false;
        }

        return true;
    }
}