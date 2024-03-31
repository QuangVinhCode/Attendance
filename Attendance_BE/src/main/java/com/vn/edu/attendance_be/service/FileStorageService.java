package com.vn.edu.attendance_be.service;

import com.vn.edu.attendance_be.config.FileStorageProperties;
import com.vn.edu.attendance_be.exeception.ClassException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path filePDFStorageLocation;

    public  String storePDFFile(MultipartFile file)
    {
        return storeFile(filePDFStorageLocation,file);
    }

    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.filePDFStorageLocation = Paths.get(fileStorageProperties.getUploadFilepdfDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(filePDFStorageLocation);
        }catch (Exception ex){
            throw new ClassException("không thể tạo thư mục lưu trữ các tập tin tải lên");
        }
    }
    private String storeFile(Path location, MultipartFile file) {
        UUID uuid = UUID.randomUUID();

        String exit = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String filename = uuid.toString() + "." + exit;

        try {
            if (filename.contains("..")){
                throw new ClassException("Xin lỗi, tên tệp chứa chuỗi đường dẫn không hợp lệ " + filename);
            }

            Path targetLocation = location.resolve(filename);
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return filename;
        }catch (Exception ex)
        {
            throw new ClassException("Không thể lưu trữ tập tin " + filename + ". Vui lòng thử lại sau");
        }
    }
    public Resource loadPDFFileAsResource(String filename)
    {
        return loadFileAsResource(filePDFStorageLocation,filename);
    }
    private Resource loadFileAsResource(Path location,String filename){
        try {
            Path filePath = location.resolve(filename).normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists())
            {
                return  resource;
            }else {
                throw  new ClassException("Không tìm thất tệp tin " + filename);
            }
        }catch (Exception ex)
        {
            throw new ClassException("Không tìm thấy tệp tin " + filename);
        }
    }


    public void  deletePDFFile(String filename){
        deleteFile(filePDFStorageLocation,filename);
    }
    private void deleteFile(Path location,String filename) {
        try {
            Path filePath = location.resolve(filename).normalize();

            if(!Files.exists(filePath))
            {
                throw  new ClassException("Không tìm thấy tệp tin " + filename);
            }

            Files.delete(filePath);
        }catch (Exception ex)
        {
            throw new ClassException("Không tim thấy tệp tin " + filename);
        }
    }
}
