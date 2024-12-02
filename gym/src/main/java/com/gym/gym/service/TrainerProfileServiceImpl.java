package com.gym.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gym.gym.domain.Files;
import com.gym.gym.domain.TrainerProfile;
import com.gym.gym.mapper.TrainerProfileMapper;

@Service
public class TrainerProfileServiceImpl implements TrainerProfileService {

    @Autowired private TrainerProfileMapper trainerProfileMapper;
    @Autowired private FileService fileService;

    @Override
    public List<TrainerProfile> list() throws Exception {
        return trainerProfileMapper.list("");
    }

    @Override
    public TrainerProfile select(int no) throws Exception {
        return trainerProfileMapper.select(no);
    }

    @Override
    public int insert(TrainerProfile trainerPofile) throws Exception {
        int result = trainerProfileMapper.insert(trainerPofile);
        List<MultipartFile> fileList = trainerPofile.getFileList();

        if( fileList != null ) 
            for (MultipartFile file : fileList) {
                // 빈 파일이 넘어온 경우 
                if (file != null && file.isEmpty()) 
                    continue;
                
                Files uploadFile = new Files();
                uploadFile.setFile(file);
                uploadFile.setProfileNo(trainerPofile.getNo());
                uploadFile.setType("main");
                fileService.upload(uploadFile);
            }
        return result;
    }

    @Override
    public int update(TrainerProfile trainerPofile) throws Exception {
        int result = trainerProfileMapper.update(trainerPofile);
        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        int result = trainerProfileMapper.delete(no);
        return result;
    }

    @Override
    public List<TrainerProfile> list(String keyword) throws Exception {
        return trainerProfileMapper.list(keyword);
    }
    
}
