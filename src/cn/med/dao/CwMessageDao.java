package cn.med.dao;



import org.apache.ibatis.annotations.Insert;

import cn.med.entity.CwMessage;

public interface CwMessageDao {


   
    
    
    
    
    /**
     * 添加消息
     * @param message
     * @return
     */
    @Insert({
        "insert into cw_message (id, user_id, ",
        "msg_content,longitude,latitude, msg_time)  ",
        "values (#{id,jdbcType=INTEGER}, #{user_id,jdbcType=INTEGER},#{from_user_id,jdbcType=INTEGER}, ",
        "#{msg_content,jdbcType=VARCHAR},#{longitude,jdbcType=VARCHAR},#{latitude,jdbcType=VARCHAR}, #{msg_time,jdbcType=VARCHAR})"
    })
    int insert(CwMessage message);
    
    
 
  
}