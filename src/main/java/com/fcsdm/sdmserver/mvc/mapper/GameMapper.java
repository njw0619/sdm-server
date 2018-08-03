package com.fcsdm.sdmserver.mvc.mapper;

import java.util.List;
import java.util.Map;

import com.fcsdm.sdmserver.mvc.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Peter on 2018-02-06.
 */
@Mapper
public interface GameMapper {    
    public int insertGame(Game game);
    public int insertAttendance(Attendance attendance);
    public int insertCarpool(Carpool carpool);
    public int insertRecord(Record record);
    public int insertScore(Score score);
    
    public int selectLatestSeq();
    public List<Score> selectScore();
    public List<Map<String, String>> selectAttendRanking();
    public List<Map<String, String>> selectGoalRanking();
    public List<Map<String, String>> selectMVPRanking();
    
}
