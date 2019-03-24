package com.surprise.am.controller;


import com.surprise.am.model.User;
import com.surprise.am.repository.UserRepository;
import com.surprise.am.security.SpringUser;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

   @Value("${image.upload.dir}")
  private String imageUploadDir;


  @PostMapping("/save/login")
  public String saveLogin(@ModelAttribute User user){
    if(userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword())!=null){
     return "redirect:/";
    }
    return "login";
  }

//  @PostMapping("/save/register")
//  public String registerUser(@ModelAttribute User user, @RequestParam("picture") MultipartFile file)
//      throws IOException , MessagingException {
//      String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//      File picture = new File(imageUploadDir + File.separator + fileName);
//      file.transferTo(picture);
//      user.setGender(Gender.MALE);
//      user.setPassword(passwordEncoder.encode(user.getPassword()));
//    user.setImagePath(fileName);
//    userRepository.save(user);
//    StringBuilder stringBuilder=new StringBuilder( "<html><head></head><body>"+"<a href='localhost://8080'/>dddd</a>"+"</body></html>");
//emailSenderService.sendSimpleMessage("stella02051994@gmail.com","ggg",stringBuilder.toString());
//
//    return "login";
//  }
@GetMapping("/loginSuccess")
public String loginSuccess(@AuthenticationPrincipal
                                   SpringUser springUser) {
  return "home";

}
  @GetMapping("/getImage")
  public void getImageAsByteArray(HttpServletResponse response, @RequestParam("imagePath") String picUrl) throws IOException {
    InputStream in = new FileInputStream(imageUploadDir + File.separator + picUrl);
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    IOUtils.copy(in, response.getOutputStream());
  }

//  @PostMapping("/save/login")
//  public String saveLogin(@ModelAttribute User user){
//    if(userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword())!=null){
//     return "redirect:/get/category";
//    }
//    return "register";
//  }
}
