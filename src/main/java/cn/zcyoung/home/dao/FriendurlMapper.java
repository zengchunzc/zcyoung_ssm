package cn.zcyoung.home.dao;

import cn.zcyoung.home.pojo.Friendurl;
import cn.zcyoung.home.pojo.FriendurlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FriendurlMapper {
    int countByExample(FriendurlExample example);

    int deleteByExample(FriendurlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Friendurl record);

    int insertSelective(Friendurl record);

    List<Friendurl> selectByExample(FriendurlExample example);

    Friendurl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Friendurl record, @Param("example") FriendurlExample example);

    int updateByExample(@Param("record") Friendurl record, @Param("example") FriendurlExample example);

    int updateByPrimaryKeySelective(Friendurl record);

    int updateByPrimaryKey(Friendurl record);
}