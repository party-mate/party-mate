package com.example.partymate.utils.namestrategy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Unagi_zoso
 * @since 2023-11-13
 */
public class ImageUtil {

    public static BufferedImage resizeImage(MultipartFile image, int maxWidth, int maxHeight) {
        BufferedImage orgImg = null;
        try {
            orgImg = ImageIO.read(image.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Scalr.resize(orgImg, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, maxWidth, maxHeight);
    }

    public static BufferedImage convertMultipartFileToBufferedImage(MultipartFile multipartFile) throws IOException {
        return ImageIO.read(multipartFile.getInputStream());
    }

    public static void saveBufferedImageToFile(BufferedImage bufferedImage, String destinationPath) {
        File destinationFile = new File(destinationPath);
        if (!destinationFile.exists()) {
            destinationFile.getParentFile().mkdirs();
        }

        // BufferedImage를 File로 저장
        try {
            ImageIO.write(bufferedImage, "jpg", destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
