package cn.zcyoung.home.dao;

import cn.zcyoung.home.pojo.Ufile;
import cn.zcyoung.home.pojo.UfileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UfileMapper {
    int countByExample(UfileExample example);

    int deleteByExample(UfileExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ufile record);

    int insertSelective(Ufile record);

    List<Ufile> selectByExample(UfileExample example);

    Ufile selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ufile record, @Param("example") UfileExample example);

    int updateByExample(@Param("record") Ufile record, @Param("example") UfileExample example);

    int updateByPrimaryKeySelective(Ufile record);

    int updateByPrimaryKey(Ufile record);
}