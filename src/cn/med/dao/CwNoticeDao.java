package cn.med.dao;



import org.apache.ibatis.annotations.Insert;

import cn.med.entity.CwMessage;
import cn.med.entity.CwNotice;

public interface CwNoticeDao {


   
    
    
    
    
    /**
     * 添加通知
     * @param message
     * @return
     */
    @Insert({
        "insert into cw_notice (id, user_id, ",
        "msg_content,longitude,latitude, msg_time)  ",
        "values (#{id,jdbcType=INTEGER}, #{user_id,jdbcType=INTEGER}, ",
        "#{msg_content,jdbcType=VARCHAR},#{longitude,jdbcType=VARCHAR},#{latitude,jdbcType=VARCHAR}, #{msg_time,jdbcType=VARCHAR})"
    })
    int insert(CwNotice cwNotice);
    
    
 
  
}