package cn.zcyoung.home.dao;

import cn.zcyoung.home.pojo.Qiandao;
import cn.zcyoung.home.pojo.QiandaoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QiandaoMapper {
    int countByExample(QiandaoExample example);

    int deleteByExample(QiandaoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Qiandao record);

    int insertSelective(Qiandao record);

    List<Qiandao> selectByExample(QiandaoExample example);

    Qiandao selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Qiandao record, @Param("example") QiandaoExample example);

    int updateByExample(@Param("record") Qiandao record, @Param("example") QiandaoExample example);

    int updateByPrimaryKeySelective(Qiandao record);

    int updateByPrimaryKey(Qiandao record);
}