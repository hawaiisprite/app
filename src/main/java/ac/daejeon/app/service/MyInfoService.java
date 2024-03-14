package ac.daejeon.app.service;


import ac.daejeon.app.common.CommonDao;
import ac.daejeon.app.common.CommonVo;
import ac.daejeon.app.common.Statics;
import ac.daejeon.app.dao.MyInfoDao;
import ac.daejeon.app.vo.MyInfoVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MyInfoService {

    private final MyInfoDao myInfoDao;

    private final CommonVo commonVo;
    private final CommonDao commonDao;


    public int submitPassportFile(MyInfoVo myInfoVo) throws IOException {

        MultipartFile passportFile = myInfoVo.getPassportFile();

        //여권 사본 pdf 파일 저장
        if(passportFile != null) {
            ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
            String year = Integer.toString(today.getYear());
            String month = Integer.toString(today.getMonth().getValue());
            String day = Integer.toString(today.getDayOfMonth());

            String fileOrigName = passportFile.getOriginalFilename();
            String fileExt = FilenameUtils.getExtension(fileOrigName);
            String fileUuid = UUID.randomUUID().toString().replace("-", "");

            boolean isSaved = Statics.zipWithPassword(fileUuid, fileExt, passportFile);

            if(isSaved == true) {
                commonVo.setFileUuid(fileUuid);
                commonVo.setFileExt(fileExt);
                commonVo.setFileOrigName(fileOrigName);
                commonVo.setFileYear(Integer.parseInt(year));
                commonVo.setFileMonth(Integer.parseInt(month));
                commonVo.setFileDay(Integer.parseInt(day));

                commonDao.saveFileInfo(commonVo);

                int insertedFilesIdx = commonVo.getFilesIdx();

                if(insertedFilesIdx != 0) {
                    myInfoVo.setPassportFileIdx(insertedFilesIdx);
                    return myInfoDao.submitPassportFile(myInfoVo);
                } else {
                    return 0;
                }

            } else {
                return 0;
            }
        }

        return 0;
    }

    public int submitProfilePhotoFile(MyInfoVo myInfoVo) throws IOException {

        MultipartFile personalInfoFile = myInfoVo.getPersonalInfoFile();

        //여권 사본 pdf 파일 저장
        if(personalInfoFile != null) {
            ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
            String year = Integer.toString(today.getYear());
            String month = Integer.toString(today.getMonth().getValue());
            String day = Integer.toString(today.getDayOfMonth());

            String fileOrigName = personalInfoFile.getOriginalFilename();
            String fileExt = FilenameUtils.getExtension(fileOrigName);
            String fileUuid = UUID.randomUUID().toString().replace("-", "");

            boolean isSaved = Statics.zipWithPassword(fileUuid, fileExt, personalInfoFile);

            if(isSaved == true) {
                commonVo.setFileUuid(fileUuid);
                commonVo.setFileExt(fileExt);
                commonVo.setFileOrigName(fileOrigName);
                commonVo.setFileYear(Integer.parseInt(year));
                commonVo.setFileMonth(Integer.parseInt(month));
                commonVo.setFileDay(Integer.parseInt(day));

                commonDao.saveFileInfo(commonVo);

                int insertedFilesIdx = commonVo.getFilesIdx();

                if(insertedFilesIdx != 0) {
                    myInfoVo.setPersonalInfoFileIdx(insertedFilesIdx);
                    return myInfoDao.submitProfilePhotoFile(myInfoVo);
                } else {
                    return 0;
                }

            } else {
                return 0;
            }
        }

        return 0;

    }

    public int submitForeignerFile(MyInfoVo myInfoVo) throws IOException {

        MultipartFile foreignerRegistrationFile = myInfoVo.getForeignerRegistrationFile();

        //여권 사본 pdf 파일 저장
        if(foreignerRegistrationFile != null) {
            ZonedDateTime today = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
            String year = Integer.toString(today.getYear());
            String month = Integer.toString(today.getMonth().getValue());
            String day = Integer.toString(today.getDayOfMonth());

            String fileOrigName = foreignerRegistrationFile.getOriginalFilename();
            String fileExt = FilenameUtils.getExtension(fileOrigName);
            String fileUuid = UUID.randomUUID().toString().replace("-", "");

            boolean isSaved = Statics.zipWithPassword(fileUuid, fileExt, foreignerRegistrationFile);

            if(isSaved == true) {
                commonVo.setFileUuid(fileUuid);
                commonVo.setFileExt(fileExt);
                commonVo.setFileOrigName(fileOrigName);
                commonVo.setFileYear(Integer.parseInt(year));
                commonVo.setFileMonth(Integer.parseInt(month));
                commonVo.setFileDay(Integer.parseInt(day));

                commonDao.saveFileInfo(commonVo);

                int insertedFilesIdx = commonVo.getFilesIdx();

                if(insertedFilesIdx != 0) {
                    myInfoVo.setForeignerRegistrationFileIdx(insertedFilesIdx);
                    return myInfoDao.submitForeignerFile(myInfoVo);
                } else {
                    return 0;
                }

            } else {
                return 0;
            }
        }

        return 0;

    }
}
