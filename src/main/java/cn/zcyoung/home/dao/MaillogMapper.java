package cn.zcyoung.home.dao;

import cn.zcyoung.home.pojo.Maillog;
import cn.zcyoung.home.pojo.MaillogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaillogMapper {
    int countByExample(MaillogExample example);

    int deleteByExample(MaillogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Maillog record);

    int insertSelective(Maillog record);

    List<Maillog> selectByExample(MaillogExample example);

    Maillog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Maillog record, @Param("example") MaillogExample example);

    int updateByExample(@Param("record") Maillog record, @Param("example") MaillogExample example);

    int updateByPrimaryKeySelective(Maillog record);

    int updateByPrimaryKey(Maillog record);
}