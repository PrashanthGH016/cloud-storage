package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> getFiles(int userId);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File getFileById(int fileId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES (#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    int insert(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    int delete(int fileId);
}

