package com.website.web.services.implementation;

import com.website.web.models.Image;
import com.website.web.models.Users;
import com.website.web.services.interfaces.IGetImageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;

@Service
public class GetImageService implements IGetImageService {
    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;
    @SneakyThrows
    @Override
    public void image(Model model,Principal principal){
        Users user = userService.getUserByPrincipal(principal);
        Image image = new Image();
        if (user.getImage() != null){
            image = imageService.getImageById(user.getImage().getId());
        } else {
/*            String imagePath = "web/src/main/resources/static/images/avatar.png";
            image.setBytes(Files.readAllBytes(Path.of(new File(imagePath).getAbsolutePath())));*/
            URL url = new URL("https://w7.pngwing.com/pngs/980/304/png-transparent-computer-icons-user-profile-avatar-heroes-silhouette-avatar.png");
            InputStream inputStream      = url.openStream();
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte [] buffer               = new byte[ 2048 ];
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            inputStream.close();
            image.setBytes(output.toByteArray());
        }
        byte[] encodeBase64 = Base64.encode(image.getBytes());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        model.addAttribute("avatar", base64Encoded);
    }
}
