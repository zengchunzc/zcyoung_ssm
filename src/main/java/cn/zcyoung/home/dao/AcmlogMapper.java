package cn.zcyoung.home.dao;

import cn.zcyoung.home.pojo.Acmlog;
import cn.zcyoung.home.pojo.AcmlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcmlogMapper {
    int countByExample(AcmlogExample example);

    int deleteByExample(AcmlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Acmlog record);

    int insertSelective(Acmlog record);

    List<Acmlog> selectByExample(AcmlogExample example);

    Acmlog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Acmlog record, @Param("example") AcmlogExample example);

    int updateByExample(@Param("record") Acmlog record, @Param("example") AcmlogExample example);

    int updateByPrimaryKeySelective(Acmlog record);

    int updateByPrimaryKey(Acmlog record);
}