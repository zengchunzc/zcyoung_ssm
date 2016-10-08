package cn.zcyoung.home.dao;

import cn.zcyoung.home.pojo.Mailyz;
import cn.zcyoung.home.pojo.MailyzExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MailyzMapper {
    int countByExample(MailyzExample example);
    
    int countTodayCount(Integer id);

    int deleteByExample(MailyzExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Mailyz record);

    int insertSelective(Mailyz record);

    List<Mailyz> selectByExample(MailyzExample example);

    Mailyz selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Mailyz record, @Param("example") MailyzExample example);

    int updateByExample(@Param("record") Mailyz record, @Param("example") MailyzExample example);

    int updateByPrimaryKeySelective(Mailyz record);

    int updateByPrimaryKey(Mailyz record);
}