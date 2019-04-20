package event.demo.service;

import event.demo.domain.BasicJoint;

public interface basicJoint {
    int deleteByPrimaryKey(Integer id);

    int insert(BasicJoint record);

    BasicJoint selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(BasicJoint record);


}
